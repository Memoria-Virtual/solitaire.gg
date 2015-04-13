package services.game

import java.util.UUID

import models._

trait GameServiceUndoHelper {
  this: GameService =>
  protected var undosByPlayer = collection.mutable.HashMap.empty[UUID, Int]

  private[this] val historyQueue = collection.mutable.Stack[ReversibleResponseMessage]()
  private[this] val undoneQueue = collection.mutable.Stack[ReversibleResponseMessage]()

  protected[this] def registerResponse(message: ResponseMessage) = message match {
    case rrm: ReversibleResponseMessage =>
      historyQueue.push(rrm)
      undoneQueue.clear()
    case _ => // no-op
  }

  protected[this] def handleUndo(accountId: UUID) = {
    if (historyQueue.isEmpty) {
      log.info("Attempt to undo with no actions available.")
    } else {
      undosByPlayer(accountId) = undosByPlayer.getOrElseUpdate(accountId, 0) + 1
      val msg = historyQueue.pop()
      val reverse = getReverse(msg)
      undoneQueue.push(reverse)
      log.info("Undoing message [" + msg.toString + "] with message [" + reverse + "] (" + historyQueue.length + " other messages in history queue).")
      sendToAll(reverse, registerUndoResponse = false)
      handleGetPossibleMoves(accountId)
    }
  }

  protected[this] def handleRedo(accountId: UUID) = {
    if (undoneQueue.isEmpty) {
      log.info("Attempt to redo from empty undo stack.")
    } else {
      val msg = undoneQueue.pop()
      val reverse = getReverse(msg)
      historyQueue.push(reverse)
      //log.info("Performing redo of [" + msg.toString + "] with message [" + reverse + "] (" + undoneQueue.length + " other messages in undo queue).")
      sendToAll(reverse, registerUndoResponse = false)
      handleGetPossibleMoves(accountId)
    }
  }

  private[this] def getReverse(rrm: ReversibleResponseMessage, completedMoves: Int = 0): ReversibleResponseMessage = {
    rrm match {
      case cr: CardRevealed =>
        cr.card.u = false
        gameState.hideCardFromAll(cr.card).headOption.getOrElse(throw new IllegalStateException("No hide response."))

      case ch: CardHidden =>
        val card = gameState.getCard(ch.id)
        card.u = true
        gameState.revealCardToAll(card).headOption.getOrElse(throw new IllegalStateException("No reveal response."))

      case cm: CardMoved =>
        val src = gameState.pilesById(cm.source)
        val tgt = gameState.pilesById(cm.target)
        val card = tgt.cards(cm.targetIndex - completedMoves)
        tgt.removeCard(card)
        val reverseTgtIdx = src.addCard(card)

        //log.info("Card [" + card + "] moved from [" + cm.source + "] to [" + cm.target + "].")

        cm.copy(
          source = cm.target,
          target = cm.source,
          turnFaceUp = cm.turnFaceDown,
          turnFaceDown = cm.turnFaceUp,
          targetIndex = reverseTgtIdx
        )

      case ms: MessageSet =>
        val sortedMessages = ms.messages.sortBy {
          case cm: CardMoved => cm.targetIndex
          case _ => 1
        }
        val ret = sortedMessages.foldLeft((0, List.empty[ReversibleResponseMessage]))((a, i) => i match {
          case cm: CardMoved => (a._1 + 1) -> (a._2 :+ getReverse(cm, a._1))
          case rrm: ReversibleResponseMessage => a._1 -> (a._2 :+ getReverse(rrm, a._1))
          case _ => a
        })._2

        //log.info("Original: " + ms)
        //log.info("Reversed: " + ret)
        MessageSet(ret)
    }
  }
}
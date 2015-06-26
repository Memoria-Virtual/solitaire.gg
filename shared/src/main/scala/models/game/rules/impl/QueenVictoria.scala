// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 *   Similar to (like): kingalbert
 *   Enable super moves, whatever those are (supermoves): 0
 */
object QueenVictoria extends GameRules(
  id = "queenvictoria",
  title = "Queen Victoria",
  like = Some("kingalbert"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/queen_victoria.htm")),
  description = "This much easier variation of ^kingalbert^ allows stacks of cards to be moved.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)
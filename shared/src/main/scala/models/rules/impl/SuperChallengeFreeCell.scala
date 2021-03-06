package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 5 (When stackable cards are removable)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Piles with low cards at bottom (T0dc): 8
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): challengefreecell
 *   Enable super moves, whatever those are (supermoves): 1
 */
object SuperChallengeFreeCell extends GameRules(
  id = "superchallengefreecell",
  completed = false,
  title = "Super Challenge FreeCell",
  like = Some("challengefreecell"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/super_challenge_freecell.htm"),
    Link("Michael Keller's amazing FreeCell FAQ", "solitairelaboratory.com/fcfaq.html#AceDepth")
  ),
  description = "A version of ^freecell^ invented by Thomas Warfield where the aces and twos are always at the bottoms of the eight stacks and wher" +
    "e spaces can only be filled by Kings.",
  layout = "f|c|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank,
      pilesWithLowCardsAtBottom = 8
    )
  ),
  cells = Some(CellRules())
)

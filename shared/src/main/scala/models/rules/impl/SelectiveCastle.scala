package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 0 (None)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): beleagueredcastle
 *   Low card (lowpip): -2 (?)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object SelectiveCastle extends GameRules(
  id = "selectivecastle",
  completed = true,
  title = "Selective Castle",
  like = Some("beleagueredcastle"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/selective_castle.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Beleaguered_Castle")
  ),
  description = "A version of ^beleagueredcastle^ where the base of the foundation is determined by the first card you play to it.",
  layout = "::f|t",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
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
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)

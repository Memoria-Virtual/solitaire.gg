package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Maximum cards per tableau (T0m): 1 (1 cards)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Tableau initial cards (T1d): 1 (1 card)
 *   Tableau piles (T1n): 12
 *   Tableau suit match rule for building (T1s): 1 (In same suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealchunk): 2 (Two at a time)
 *   Deal cards from stock (dealto): 11
 *   Similar to (like): carthage
 *   Number of decks (ndecks): 3 (3 decks)
 */
object Algiers extends GameRules(
  id = "algiers",
  completed = true,
  title = "Algiers",
  like = Some("carthage"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/algiers.htm")),
  description = "A three-deck variation of ^carthage^.",
  layout = "f|s.t|t",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauFirstSet,
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(2)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 9,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      maxCards = 1
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)

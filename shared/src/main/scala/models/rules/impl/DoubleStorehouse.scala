package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Reserve name (R0Nm): Storehouse
 *   Reserve initial cards (R0d): 19
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): storehouse
 *   Low card (lowpip): 2 (2)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object DoubleStorehouse extends GameRules(
  id = "doublestorehouse",
  completed = true,
  title = "Double Storehouse",
  like = Some("storehouse"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_storehouse.htm")),
  description = "A two-deck version of ^storehouse^.",
  layout = "swf|r::.t",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Two
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Storehouse",
      numPiles = 1,
      initialCards = 19,
      cardsFaceDown = -1
    )
  )
)

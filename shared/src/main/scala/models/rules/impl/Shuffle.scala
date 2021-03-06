package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): neptune
 *   Number of decks (ndecks): 1 (1 deck)
 *   Card removal method (pairs): 22 (Remove pairs with consecutive ranks or A-K)
 *   Custom suits (suits): 0
 *   Victory condition (victory): 0 (All cards on foundation)
 */
object Shuffle extends GameRules(
  id = "shuffle",
  completed = true,
  title = "Shuffle",
  like = Some("neptune"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/shuffle.htm")),
  description = "A version of ^neptune^ where you can also pair kings with aces.",
  layout = "s.:f|t",
  cardRemovalMethod = CardRemovalMethod.RemoveConsecutiveRankPairsOrAK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
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
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)

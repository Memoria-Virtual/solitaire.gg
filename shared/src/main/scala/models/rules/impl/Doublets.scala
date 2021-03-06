package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Maximum cards for foundation (F0m): 0
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): simplepairs
 *   Card removal method (pairs): 1 (Remove pairs of same rank)
 */
object Doublets extends GameRules(
  id = "doublets",
  completed = true,
  title = "Doublets",
  like = Some("simplepairs"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/doublets.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/doublets.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/doublets.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/doublets.php"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/doublet.html"),
    Link("Elton Gahr on HobbyHub", "www.hobbyhub360.com/index.php/solitaire-how-to-play-doublets-14100/")
  ),
  description = "A similar game to ^simplepairs^. You remove pairs of cards of the same rank. The only way to bring any strategy into the game is b" +
    "y using the undo button.",
  layout = ".:s.:f|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)

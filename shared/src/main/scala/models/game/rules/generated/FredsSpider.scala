// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FredsSpider extends GameRules(
  id = "fredsspider",
  title = "Fred's Spider",
  like = Some("spider"),
  description = "In this easy variation on ^spider^, designed by Fred Lunde of Livonia, Michigan, cards are dealt face up and can be moved to the f" +
  "oundation singly.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

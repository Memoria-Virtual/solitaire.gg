// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object ThirtyNineSteps extends GameRules(
  id = "thirtyninesteps",
  title = "Thirty Nine Steps",
  like = Some("waningmoon"),
  related = Seq("lucas"),
  description = "^waningmoon^ with fewer cards in the initial tableau.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

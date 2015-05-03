// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object GoodMeasure extends GameRules(
  id = "goodmeasure",
  title = "Good Measure",
  like = Some("bakersdozen"),
  description = "A much more difficult variation of ^bakersdozen^ with fewer tableau piles.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 2,
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  ),
  complete = false
)

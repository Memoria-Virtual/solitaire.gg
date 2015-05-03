// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Inquisitor extends GameRules(
  id = "inquisitor",
  title = "Inquisitor",
  like = Some("ladyjane"),
  related = Seq("quizzie"),
  description = "This ^ladyjane^ variation by Thomas Warfield requires you to build in suit, but gives you an extra redeal.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

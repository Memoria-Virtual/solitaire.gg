// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Dover extends GameRules(
  id = "dover",
  title = "Dover",
  like = Some("bristol"),
  description = "This two-deck version of ^bristol^ still has three waste piles, but foundation piles must be built up in suit and empty tableau sp" +
  "aces may be filled, though only from the waste.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
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
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation")
    )
  ),
  complete = false
)

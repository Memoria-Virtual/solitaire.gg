// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object NorthwestTerritory extends GameRules(
  id = "northwestterritory",
  title = "Northwest Territory",
  description = "A cross between ^flowergarden^ and ^klondike^, slightly easier than the similar ^klondiketerritory^ game.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

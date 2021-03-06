package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Roosevelt extends GameRules(
  id = "roosevelt",
  completed = false,
  title = "Roosevelt",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/roosevelt.htm")),
  description = "A very hard ^fortythieves^ variant where we build by alternate colors on only seven tableau piles.",
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)

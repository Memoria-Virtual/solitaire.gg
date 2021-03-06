package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): busyaces
 *   Low card (lowpip): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): jacksinthebox, threescompany, castoutnines
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Deuces extends GameRules(
  id = "deuces",
  completed = true,
  title = "Deuces",
  like = Some("busyaces"),
  related = Seq("jacksinthebox", "threescompany", "castoutnines"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Deuces_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/deuces.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/deuces.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/deuces.htm")
  ),
  description = "A more difficult variation of ^busyaces^ with fewer tableau piles.",
  layout = "swf|.t",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Two
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
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)

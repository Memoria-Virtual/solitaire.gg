package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 102
 *   Empty tableau is filled with (T0f): 4
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Related games (related): stewart
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Martha extends GameRules(
  id = "martha",
  completed = true,
  title = "Martha",
  related = Seq("stewart"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/martha.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Martha_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/martha.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/martha.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/martha.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Martha.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Martha%20Solitaire.shtml")
  ),
  description = "An easy game with no stock where half the cards start face down. Somewhat similar to ^bakersdozen^.",
  layout = "::::f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)

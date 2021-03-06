package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 2 (2 stacks)
 *   Foundation suit match rule (F0s): 3 (In same color)
 *   Initial card restriction (F0u): 1 (Unique colors)
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Can move cards from foundation (F1mb): 1 (Always)
 *   Number of foundation piles (F1n): 2 (2 stacks)
 *   Foundation suit match rule (F1s): 3 (In same color)
 *   Initial card restriction (F1u): 1 (Unique colors)
 *   Auto-move cards to foundation (F2a): 0 (Never)
 *   Foundation add complete sequences only (F2cs): true
 *   Number of foundation piles (F2n): 4 (4 stacks)
 *   Foundation suit match rule (F2s): 4 (In alternating colors)
 *   Foundation Sets (Fn): 3
 *   Tableau initial cards (T0d): -2 (custom)
 *   Tableau cards face down (T0df): 100
 *   Custom initial cards (T0ds): DDDDDDDU DDDDDDU DDDDDU DDDDU DDDU DDU DU U U
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): diavolo
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): rougeforty
 */
object RougeEtNoir extends GameRules(
  id = "rougeetnoir",
  completed = false,
  title = "Rouge et Noir",
  like = Some("diavolo"),
  related = Seq("rougeforty"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Rouge_et_Noir"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rouge_et_noir.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/rouge_et_noir.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/RougeEtNoir.html"),
    Link("An 1898 description", "howtoplaysolitaire.blogspot.com/2010/06/rouge-et-noir-double-deck-solitaire.html")
  ),
  description = "A variant of ^diavolo^ with a different tableau and no waste. Invented by Charles Jewell.",
  layout = "sfff|t",
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
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      suitMatchRule = SuitMatchRule.SameColor
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      suitMatchRule = SuitMatchRule.SameColor
    ),
    FoundationRules(
      setNumber = 2,
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDDDU",
        "DDDDDDU",
        "DDDDDU",
        "DDDDU",
        "DDDU",
        "DDU",
        "DU",
        "U",
        "U"
      ),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

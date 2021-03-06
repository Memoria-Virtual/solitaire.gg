package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUU UUUUUU UUUUUU UUUUUU UUUUU UUUUU UUUUU UUUUU UUUUU UUUUU
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 3 (To all tableau piles if none empty)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): spider
 *   Number of decks (ndecks): 2 (2 decks)
 *   Right mouse interface function (rightfunc): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object Beetle extends GameRules(
  id = "beetle",
  completed = false,
  title = "Beetle",
  like = Some("spider"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Beetle_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/beetle.htm"),
    Link("Chet Carrie on eHow", "www.ehow.com/how_7215429_play-beetle-solitaire.html")
  ),
  description = "A variation of ^spider^ where all cards are dealt face up.",
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUU",
        "UUUUUU",
        "UUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)

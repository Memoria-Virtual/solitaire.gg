package models.rules

sealed trait TableauFaceDownCards
object TableauFaceDownCards {
  case class Count(n: Int) extends TableauFaceDownCards
  case object AllButOne extends TableauFaceDownCards
  case object EvenNumbered extends TableauFaceDownCards
  case object OddNumbered extends TableauFaceDownCards
}

sealed trait TableauAutoFillEmptyFrom
object TableauAutoFillEmptyFrom {
  case object Nowhere extends TableauAutoFillEmptyFrom
  case object Reserve extends TableauAutoFillEmptyFrom
  case object Stock extends TableauAutoFillEmptyFrom
  case object Waste extends TableauAutoFillEmptyFrom
  case object WasteThenStock extends TableauAutoFillEmptyFrom
  case object StockThenWaste extends TableauAutoFillEmptyFrom
  case object NextPile extends TableauAutoFillEmptyFrom
}

sealed trait PileAction
object PileAction {
  case object None extends PileAction
  case object MoveKingsToBottom extends PileAction
  case object MoveToFoundation extends PileAction
  case object MoveToFoundationAndReplace extends PileAction
  case object MoveToEmptyFoundation extends PileAction
  case object MoveToEmptyFoundationAndReplace extends PileAction
  case object LimitToTwoJacks extends PileAction
}

case class TableauRules(
  name: String = "Tableau",
  setNumber: Int = 0,
  numPiles: Int = 7,
  cardsShown: Int = 0,
  initialCards: InitialCards = InitialCards.PileIndex,
  customInitialCards: Seq[String] = Seq.empty,
  uniqueRanks: Boolean = false,
  cardsFaceDown: TableauFaceDownCards = TableauFaceDownCards.AllButOne,
  suitMatchRuleForBuilding: SuitMatchRule = SuitMatchRule.AlternatingColors,
  rankMatchRuleForBuilding: RankMatchRule = RankMatchRule.Down,
  wrap: Boolean = false,
  suitMatchRuleForMovingStacks: SuitMatchRule = SuitMatchRule.AlternatingColors,
  rankMatchRuleForMovingStacks: RankMatchRule = RankMatchRule.Down,

  autoFillEmptyFrom: TableauAutoFillEmptyFrom = TableauAutoFillEmptyFrom.Nowhere,
  emptyFilledWith: FillEmptyWith = FillEmptyWith.Any,

  mayMoveToNonEmptyFrom: Seq[String] = GameRules.allSources,
  mayMoveToEmptyFrom: Seq[String] = GameRules.allSources,

  maxCards: Int = 0,
  actionDuringDeal: PileAction = PileAction.None,
  actionAfterDeal: PileAction = PileAction.None,
  pilesWithLowCardsAtBottom: Int = 0
)

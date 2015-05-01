package services.test

import models.GameMessage
import models.game.rules.GameRulesSet
import models.test.{ Test, Tree }

object SolverTests {
  val solvableSeeds = Map(
    "klondike" -> 11,
    "pyramid" -> 2,
    "spider" -> 50,
    "trustyTwelve" -> 3
  )
}

class SolverTests {
  val all = Tree(Test("solver"), GameRulesSet.completed.map(x => testSolver(x).toTree))

  def testSolver(rules: String) = Test("solver-" + rules, { () =>
    val seed = SolverTests.solvableSeeds.getOrElse(rules, 1)
    runSolver(rules, seed)
  })

  private[this] def runSolver(rules: String, seed: Int) = {
    val solver = GameSolver(rules, 0, Some(seed))
    val maxMoves = 2000
    val movesPerformed = collection.mutable.ArrayBuffer.empty[GameMessage]
    while (!solver.gameWon && movesPerformed.size < maxMoves) {
      val move = solver.performMove()
      movesPerformed += move
    }

    if (solver.gameWon) {
      val msg = "Won game [" + rules + "] with seed [" + seed + "] in [" + movesPerformed.size + "] moves."
      msg
    } else {
      val msg = "Unable to find a solution for [" + rules + "] seed [" + seed + "] in [" + movesPerformed.size + "] moves."
      msg
    }
  }
}

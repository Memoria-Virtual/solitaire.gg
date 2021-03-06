package models.pile.set

import models.pile.Pile

case class PileSet(behavior: String, piles: Seq[Pile], visible: Boolean) {
  for (p <- piles) {
    p.pileSet = Some(this)
  }
}

package cl.uchile.dcc
import jokers._

trait Scorable {
  def applyScore(score: Score, j: Joker) : Score
}

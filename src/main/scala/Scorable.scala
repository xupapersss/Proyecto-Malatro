package cl.uchile.dcc
import Jokers._

trait Scorable {
  def applyScore(score: Score, j: Joker) : Score
}

package cl.uchile.dcc
import jokers._

import scala.collection.mutable.ArrayBuffer


trait Scorable {
  def applyScore(score: Score, j: Joker) : Score
}

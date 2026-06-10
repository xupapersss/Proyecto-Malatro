package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Nine card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */
class Nine extends Odd {
  val order: Int = 9
  val value: Int = 9

  override def applyScore(score: Score, j: Joker): Score = {
    j.nineScore(score, this)
  }
}

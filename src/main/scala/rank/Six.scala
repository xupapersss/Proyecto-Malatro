package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Six card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Six extends Even {
  val order: Int = 6
  val value: Int = 6

  override def applyScore(score: Score, j: Joker): Score = {
    j.sixScore(score, this)
  }
}

package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Ten card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Ten extends Even {
  val order: Int = 10
  val value: Int = 10

  override def applyScore(score: Score, j: Joker): Score = {
    j.tenScore(score, this)
  }
}

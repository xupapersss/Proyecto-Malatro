package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Eight card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Eight extends Even {
  val order: Int = 8
  val value: Int = 8

  override def applyScore(score: Score, j: Joker): Score = {
    j.eightScore(score, this)
  }
}

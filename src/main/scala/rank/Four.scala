package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Four card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Four extends Even {
  val order: Int = 4
  val value: Int = 4

  override def applyScore(score: Score, j: Joker): Score = {
    j.fourScore(score, this)
  }
}


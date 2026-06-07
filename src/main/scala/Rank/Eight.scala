package cl.uchile.dcc
package Rank
import Jokers._

/**
 * Represents the Eight card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Eight extends Rank {
  val order: Int = 8
  val value: Int = 8
  override val category = "Even"

  override def applyScore(score: Score, j: Joker): Score = {
    j.eightScore(score, this)
  }
}

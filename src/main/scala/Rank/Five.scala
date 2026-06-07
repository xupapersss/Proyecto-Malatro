package cl.uchile.dcc
package Rank
import Jokers._

/**
 * Represents the Five card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */
class Five extends Rank {
  val order: Int = 5
  val value: Int = 5
  override val category = "Odd"

  override def applyScore(score: Score, j: Joker): Score = {
    j.fiveScore(score, this)
  }
}

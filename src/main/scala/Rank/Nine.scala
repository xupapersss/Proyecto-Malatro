package cl.uchile.dcc
package Rank
import Jokers._

/**
 * Represents the Nine card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */
class Nine extends Rank {
  val order: Int = 9
  val value: Int = 9
  override val category = "Odd"

  override def applyScore(score: Score, j: Joker): Score = {
    j.nineScore(score, this)
  }
}

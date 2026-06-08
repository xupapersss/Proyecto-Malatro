package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Seven card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */
class Seven extends Rank {
  val order: Int = 7
  val value: Int = 7
  override val category = "Odd"

  override def applyScore(score: Score, j: Joker): Score = {
    j.sevenScore(score, this)
  }
}

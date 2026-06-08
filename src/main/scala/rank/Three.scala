package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Three card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */
class Three extends Rank {
  val order: Int = 3
  val value: Int = 3
  override val category = "Odd"

  override def applyScore(score: Score, j: Joker): Score = {
    j.threeScore(score, this)
  }
}


package cl.uchile.dcc
package Rank
import Jokers._

/**
 * Represents the Six card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Six extends Rank {
  val order: Int = 6
  val value: Int = 6
  val category = "Even"

  override def applyScore(score: Score, j: Joker): Score = {
    j.sixScore(score, this)
  }
}

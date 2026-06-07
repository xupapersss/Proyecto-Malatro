package cl.uchile.dcc
package Rank
import Jokers._

/**
 * Represents the Two card rank.
 *
 * This is a numeric card with a low order and value.
 *
 * Category: Even
 */
class Two extends Rank {
  val order: Int = 2
  val value: Int = 2
  val category = "Even"

  override def applyScore(score: Score, j: Joker): Score = {
    j.twoScore(score, this)
  }
}

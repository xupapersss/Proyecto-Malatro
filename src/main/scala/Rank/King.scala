package cl.uchile.dcc
package Rank
import Jokers._

/**
 * Represents the King card rank.
 *
 * This is the highest face card with a fixed scoring value.
 *
 * Category: Face Card
 */
class King extends Rank {
  val order: Int = 13
  val value: Int = 10
  override val category = "Face Card"

  override def applyScore(score: Score, j: Joker): Score = {
    j.kingScore(score, this)
  }
}

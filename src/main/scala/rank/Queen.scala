package cl.uchile.dcc
package rank
import jokers._
/**
 * Represents the Queen card rank.
 *
 * This is a face card with a fixed scoring value and high order.
 *
 * Category: Face Card
 */
class Queen extends Rank {
  val order: Int = 12
  val value: Int = 10
  override val category = "Face Card"

  override def applyScore(score: Score, j: Joker): Score = {
    j.queenScore(score, this)
  }
}


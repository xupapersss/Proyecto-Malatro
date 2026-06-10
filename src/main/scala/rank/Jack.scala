package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Jack card rank.
 *
 * This is a face card with a fixed scoring value and higher order.
 *
 * Category: Face Card
 */
class Jack extends FaceCard {
  val order: Int = 11
  val value: Int = 10

  override def applyScore(score: Score, j: Joker): Score = {
    j.jackScore(score, this)
  }
}

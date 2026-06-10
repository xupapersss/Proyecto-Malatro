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
class Queen extends FaceCard {
  val order: Int = 12
  val value: Int = 10

  /**
   * Applies the scoring effect of a Joker specifically to a Queen rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Queen
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.queenScore(score, this)
  }
}
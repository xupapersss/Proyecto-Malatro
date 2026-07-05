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

  override def getRankName: String = "Jack"
  
  /**
   * Applies the scoring effect of a Joker specifically to a Jack rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Jack
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.jackScore(score, this)
  }
}
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
  /** The relative order of the Jack in a sequence. */
  val order: Int = 11
  /** The standard scoring value of the Jack. */
  val value: Int = 10

  /**
   * @return the string "Jack"
   */
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
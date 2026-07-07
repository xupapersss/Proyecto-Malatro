package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Two card rank.
 *
 * This is a numeric card with a low order and value.
 *
 * Category: Even
 */
class Two extends Even {
  /** The relative order of the Two in a sequence. */
  val order: Int = 2
  /** The standard scoring value of the Two. */
  val value: Int = 2

  /**
   * @return the string "Two"
   */
  override def getRankName: String = "Two"

  /**
   * Applies the scoring effect of a Joker specifically to a Two rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Two
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.twoScore(score, this)
  }
}
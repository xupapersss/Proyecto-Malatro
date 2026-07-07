package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Eight card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Eight extends Even {
  /** The relative order of the Eight in a sequence. */
  val order: Int = 8
  /** The standard scoring value of the Eight. */
  val value: Int = 8

  /**
   * @return the string "Eight"
   */
  override def getRankName: String = "Eight"

  /**
   * Applies the scoring effect of a Joker specifically to an Eight rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Eight
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.eightScore(score, this)
  }
}
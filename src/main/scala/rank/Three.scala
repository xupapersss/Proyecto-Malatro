package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Three card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */
class Three extends Odd {
  /** The relative order of the Three in a sequence. */
  val order: Int = 3
  /** The standard scoring value of the Three. */
  val value: Int = 3

  /**
   * @return the string "Three"
   */
  override def getRankName: String = "Three"

  /**
   * Applies the scoring effect of a Joker specifically to a Three rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Three
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.threeScore(score, this)
  }
}
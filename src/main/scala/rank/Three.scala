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
  val order: Int = 3
  val value: Int = 3

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
package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Four card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Four extends Even {
  val order: Int = 4
  val value: Int = 4

  /**
   * Applies the scoring effect of a Joker specifically to a Four rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Four
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.fourScore(score, this)
  }
}
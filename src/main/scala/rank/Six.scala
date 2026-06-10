package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Six card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Six extends Even {
  val order: Int = 6
  val value: Int = 6

  /**
   * Applies the scoring effect of a Joker specifically to a Six rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Six
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.sixScore(score, this)
  }
}
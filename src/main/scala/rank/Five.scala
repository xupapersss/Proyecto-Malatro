package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Five card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */
class Five extends Odd {
  val order: Int = 5
  val value: Int = 5

  override def getRankName: String = "Five"
  
  /**
   * Applies the scoring effect of a Joker specifically to a Five rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Five
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.fiveScore(score, this)
  }
}
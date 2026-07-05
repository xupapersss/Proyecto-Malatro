package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Nine card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */
class Nine extends Odd {
  val order: Int = 9
  val value: Int = 9

  override def getRankName: String = "Nine"
  
  
  /**
   * Applies the scoring effect of a Joker specifically to a Nine rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Nine
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.nineScore(score, this)
  }
}
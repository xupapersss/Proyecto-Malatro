package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the Ten card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Even
 */
class Ten extends Even {
  val order: Int = 10
  val value: Int = 10

  override def getRankName: String = "Ten"
  
  
  /**
   * Applies the scoring effect of a Joker specifically to a Ten rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Ten
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.tenScore(score, this)
  }
}
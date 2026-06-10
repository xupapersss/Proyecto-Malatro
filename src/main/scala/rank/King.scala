package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the King card rank.
 *
 * This is the highest face card with a fixed scoring value.
 *
 * Category: Face Card
 */
class King extends FaceCard {
  val order: Int = 13
  val value: Int = 10

  /**
   * Applies the scoring effect of a Joker specifically to a King rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the King
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.kingScore(score, this)
  }
}
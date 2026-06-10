package cl.uchile.dcc
package suit
import jokers._

/**
 * Represents the Hearts suit.
 *
 * All cards of this suit share the same label: "Hearts".
 */
class Hearts extends Suit {
  override val label = "Hearts"

  /**
   * Applies the scoring effect of a Joker specifically to a Hearts suit via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Hearts suit
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.heartsScore(score, this)
  }

}

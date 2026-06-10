package cl.uchile.dcc
package suit
import jokers._

/**
 * Represents the Diamonds suit.
 *
 * All cards of this suit share the same label: "Diamonds".
 */
class Diamonds extends Suit {
  override val label = "Diamonds"

  /**
   * Applies the scoring effect of a Joker specifically to a Diamonds suit via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Diamonds suit
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.diamondsScore(score, this)
  }
}
package cl.uchile.dcc
package suit
import jokers._

import scala.collection.mutable.ArrayBuffer

/**
 * Represents the Spades suit.
 *
 * All cards of this suit share the same label: "Spades".
 */
class Spades extends Suit {
  override val label = "Spades"

  /**
   * Applies the scoring effect of a Joker specifically to a Spades suit via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Spades suit
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.spadesScore(score, this)
  }
}
package cl.uchile.dcc
package suit

import jokers._

/**
 * Represents the Clubs suit.
 *
 * All cards of this suit share the same label: "Clubs".
 */
class Clubs extends Suit {
  /** The human-readable label for this suit. */
  override val label = "Clubs"

  /**
   * Applies the scoring effect of a Joker specifically to a Clubs suit via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Clubs suit
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.clubsScore(score, this)
  }
}
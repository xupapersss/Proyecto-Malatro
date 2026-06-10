package cl.uchile.dcc
package rank

import jokers._

/**
 * Represents the Ace card rank.
 *
 * The Ace is a special rank that can act as the lowest value in sequences
 * (e.g., A-2-3-4-5) and has a higher scoring value compared to number cards.
 *
 * Category: Odd
 */
class Ace extends Odd {
  val order: Int = 1
  val value: Int = 11

  /**
   * Applies the scoring effect of a Joker specifically to an Ace rank via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Ace
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.aceScore(score, this)
  }
}
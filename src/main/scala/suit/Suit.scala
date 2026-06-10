package cl.uchile.dcc
package suit

import jokers._

/**
 * Represents the suit of a playing card.
 *
 * A Suit defines the category of a card based on its symbol,
 * such as Hearts, Spades, Clubs, or Diamonds.
 *
 * Suits are typically used to evaluate combinations like Flush,
 * where all cards must share the same suit.
 */
trait Suit{

  /**
   * A human-readable label that identifies the suit.
   *
   * Examples:
   * - "Hearts"
   * - "Spades"
   * - "Clubs"
   * - "Diamonds"
   */
  def label : String

  /**
   * Applies the scoring effect of a Joker to this suit.
   *
   * This method acts as the entry point for double dispatch, allowing the suit
   * to pass itself to the Joker for specific score evaluations.
   *
   * @param score the current score before applying the joker's effect
   * @param j     the Joker being applied
   * @return the updated score after the joker's effect is calculated
   */
  def applyScore(score: Score, j: Joker): Score
}
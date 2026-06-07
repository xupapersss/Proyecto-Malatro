package cl.uchile.dcc
package Suit

import Jokers._

/**
 * Represents the suit of a playing card.
 *
 * A Suit defines the category of a card based on its symbol,
 * such as Hearts, Spades, Clubs, or Diamonds.
 *
 * Suits are typically used to evaluate combinations like Flush,
 * where all cards must share the same suit.
 */
trait  Suit extends Scorable {

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
  
  override def applyScore(score: Score, j: Joker): Score = {
    j.suitScore(score, this)
  }
  
}

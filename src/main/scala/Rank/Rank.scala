package cl.uchile.dcc
package Rank

/**
 * Represents the rank of a playing card in the game.
 *
 * A Rank defines the intrinsic properties of a card that are independent of its suit,
 * such as its relative position in sequences, its scoring value, and its category.
 *
 * Each concrete implementation corresponds to a specific card rank (e.g., Ace, Two, Jack, etc.).
 */

trait Rank {

  /**
   * The relative order of the rank in a sequence.
   *
   * This is used to evaluate combinations such as straights.
   * For example:
   * - Ace may be represented as 1
   * - Jack as 11, Queen as 12, King as 13
   */

  def order: Int

  /**
   * The scoring value associated with the rank.
   *
   * This is used to calculate the total score of a hand.
   * For example:
   * - Number cards typically have their numeric value
   * - Face cards (Jack, Queen, King) usually have value 10
   * - Ace may have a special value (e.g., 11)
   */

  def value: Int

  /**
   * The category of the rank.
   *
   * This classifies the rank into a general group, such as:
   * - "Even"
   * - "Odd"
   * - "Face Card"
   *
   * This can be used for special rules or card effects.
   */
  
  def category: String


}

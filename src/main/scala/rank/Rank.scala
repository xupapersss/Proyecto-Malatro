package cl.uchile.dcc
package rank

import jokers.*

/**
 * Represents the rank of a playing card in the game.
 *
 * A Rank defines the intrinsic properties of a card that are independent of its suit,
 * such as its relative position in sequences, its scoring value, and its category.
 *
 * Each concrete implementation corresponds to a specific card rank (e.g., Ace, Two, Jack, etc.).
 */
trait Rank{

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
   * Applies the scoring effect of a Joker to this rank.
   *
   * This method acts as the entry point for double dispatch, allowing the rank
   * to pass itself to the Joker for specific score evaluations.
   *
   * @param score the current score before applying the joker's effect
   * @param j     the Joker being applied
   * @return the updated score after the joker's effect is calculated
   */
  def applyScore(score: Score, j: Joker): Score

  def isEven: Boolean = false

  def isOdd : Boolean = false

  def isFaceCard : Boolean = false
}


/**
 * Represents a rank category for odd-valued cards.
 */
trait Odd extends Rank {
  override def isOdd: Boolean = true
}

/**
 * Represents a rank category for even-valued cards.
 */
trait Even extends Rank {
  override def isEven: Boolean = true
}

/**
 * Represents a rank category for face cards (e.g., Jack, Queen, King).
 */
trait FaceCard extends Rank {
  override def isFaceCard: Boolean = true
}
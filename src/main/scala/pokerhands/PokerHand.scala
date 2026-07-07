package cl.uchile.dcc
package pokerhands

import jokers.Joker

import scala.collection.mutable.ArrayBuffer

/**
 * Represents a poker hand combination in the game.
 *
 * A PokerHand defines a specific type of card combination
 * (e.g., Pair, Straight, Flush) and its associated scoring values.
 *
 * Each implementation corresponds to a particular hand type
 * and provides the base score used to calculate the final result.
 */
trait PokerHand{

  /**
   * The base number of chips awarded for this hand.
   *
   * This value represents the initial score obtained
   * before applying any multipliers.
   */
  def basechips: Int

  /**
   * The multiplier applied to the base chips.
   *
   * The final score of a hand is typically computed as:
   * basechips * basemult
   */
  def basemult: Int

  /**
   * Applies the scoring effect of a Joker to this poker hand.
   *
   * This method acts as the entry point for double dispatch, allowing the hand
   * to pass itself to the Joker for specific score evaluations.
   *
   * @param score the current score before applying the joker's effect
   * @param j     the Joker being applied
   * @return the updated score after the joker's effect is calculated
   */
  def applyScore(score: Score, j : Joker): Score

  /**
   * Returns the specific cards that form the core combination of this hand.
   *
   * By default, it returns all played cards. Specific hands (like Pair or Three of a Kind)
   * override this to return only the subset of cards that actively contribute to the score.
   *
   * @param playedCards the list of cards played by the user
   * @return an ArrayBuffer containing only the scoring cards
   */
  def scoringCards(playedCards: ArrayBuffer[Card]): ArrayBuffer[Card] = {
    playedCards
  }
}
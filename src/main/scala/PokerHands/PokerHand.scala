package cl.uchile.dcc
package PokerHands

import Jokers.Joker

/**
 * Represents a poker hand combination in the game.
 *
 * A PokerHand defines a specific type of card combination
 * (e.g., Pair, Straight, Flush) and its associated scoring values.
 *
 * Each implementation corresponds to a particular hand type
 * and provides the base score used to calculate the final result.
 */
trait PokerHand extends Scorable {

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

  override def applyScore(score: Score, j: Joker): Score = {
    j.pokerHandScore(score, this)
  }
}

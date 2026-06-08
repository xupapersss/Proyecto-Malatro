package cl.uchile.dcc
package pokerhands

import cl.uchile.dcc.jokers.Joker

import scala.collection.mutable.ArrayBuffer

/**
 * Represents the High Card poker hand.
 *
 * This is the lowest-ranking hand in the game and is used when
 * no other combination is satisfied.
 *
 * HighCard acts as a fallback case and is always considered valid.
 */

class HighCard extends PokerHand {

  /**
   * The base number of chips awarded for a High Card hand.
   */
  override val basechips = 5

  /**
   * The multiplier applied to the base chips.
   */
  override val basemult = 1

  /**
   * Determines if a set of cards qualifies as a High Card.
   *
   * Since High Card is the fallback case, this method always returns true.
   *
   * @param cards the cards to evaluate
   * @return true for any set of cards
   */
  def isHCard(cards: ArrayBuffer[Card]): Boolean = true

  override def applyScore(score: Score, j: Joker): Score = {
    j.highCardScore(score, this)
  }
}

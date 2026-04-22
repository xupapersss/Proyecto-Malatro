package cl.uchile.dcc
package PokerHands

import scala.collection.mutable.ArrayBuffer


/**
 * Represents the Straight Flush poker hand.
 *
 * A Straight Flush is formed when a set of cards is both:
 * - a Straight (consecutive ranks)
 * - a Flush (same suit)
 *
 * This is the highest-ranking hand in the current system.
 */
class StraightFlush extends PokerHand {

  /**
   * The base number of chips awarded for a Straight Flush.
   */
  override val basechips = 100

  /**
   * The multiplier applied to the base chips.
   */
  override val basemult = 8


  /**
   * Determines if a set of cards forms a Straight Flush.
   *
   * This method combines the logic of Straight and Flush by
   * checking both conditions on the same set of cards.
   *
   * @param cards the cards to evaluate
   * @return true if the cards form both a Straight and a Flush
   */
  def isStraightFlush(cards: ArrayBuffer[Card]): Boolean = {
    val straight = new Straight
    val flush = new Flush

    straight.isStraight(cards) && flush.isFlush(cards)
  }
}
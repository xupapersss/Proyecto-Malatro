package cl.uchile.dcc
package pokerhands

import jokers._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


/**
 * Represents the Flush poker hand.
 *
 * A Flush is formed when all cards in the hand share the same suit.
 *
 * This hand is evaluated by counting how many times each suit appears
 * and checking if all cards belong to a single suit.
 */
class Flush extends PokerHand {

  /**
   * The base number of chips awarded for a Flush.
   */

  val basechips = 35

  /**
   * The multiplier applied to the base chips.
   */
  val basemult = 4


  /**
   * Determines if a set of cards forms a Flush.
   *
   * This method counts occurrences of each suit using their labels
   * and checks whether all cards share the same suit.
   *
   * @param cards the cards to evaluate
   * @return true if all cards have the same suit
   */
  def isFlush(cards: ArrayBuffer[Card]): Boolean = {

    if (cards.length != 5) return false
    val counter = mutable.Map[String, Int]()

    for (card <- cards) {
      val suit = card.suit.label
      counter(suit) = counter.getOrElse(suit, 0) + 1
    }
    counter.values.exists(_ == 5)
  }

  /**
   * Applies the scoring effect of a Joker specifically to a Flush hand via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Flush
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.flushScore(score, this)
  }
}
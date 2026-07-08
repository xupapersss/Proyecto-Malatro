package cl.uchile.dcc
package pokerhands

import scala.collection.mutable.ArrayBuffer
import jokers._

/**
 * Represents the Straight poker hand.
 *
 * A Straight is formed when five cards have consecutive ranks,
 * regardless of their suits.
 *
 * This implementation supports both:
 * - low Ace sequences (A-2-3-4-5)
 * - high Ace sequences (10-J-Q-K-A)
 */
class Straight extends PokerHand {

  /**
   * The base number of chips awarded for a Straight.
   */
  override val basechips = 30

  /**
   * The multiplier applied to the base chips.
   */
  override val basemult = 4

  /**
   * Determines whether a sequence of rank orders is consecutive.
   *
   * This method:
   * - removes duplicate values
   * - sorts the ranks
   * - checks if each element differs by exactly 1 from the next
   *
   * @param orders the list of rank orders to evaluate
   * @return true if the values form a strictly consecutive sequence
   */
  private def isConsecutive(orders: ArrayBuffer[Int]): Boolean = {
    val unique = orders.distinct

    if (unique.length != orders.length) {
      false
    } else {
      val sorted = unique.sorted
      var i = 0

      while (i < sorted.length - 1) {
        if (sorted(i + 1) != sorted(i) + 1) {
          return false
        }
        i += 1
      }

      true
    }
  }

  /**
   * Determines if a set of cards forms a Straight.
   *
   * This method evaluates two cases:
   * 1. Normal consecutive sequence using standard rank order
   * 2. High Ace case, where Ace (1) is treated as 14
   *
   * @param cards the cards to evaluate
   * @return true if the cards form a valid Straight
   */
  def isStraight(cards: ArrayBuffer[Card]): Boolean = {

    if (cards.length != 5) return false
    val orders = cards.map(_.rank.order)

    if (isConsecutive(orders)) {
      true
    } else {
      val highAceOrders = orders.map(order => if (order == 1) 14 else order)
      isConsecutive(highAceOrders)
    }
  }

  /**
   * Applies the scoring effect of a Joker specifically to a Straight hand via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Straight
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.straightScore(score, this)
  }

  override def pokerHandName: String = "Straight"
}
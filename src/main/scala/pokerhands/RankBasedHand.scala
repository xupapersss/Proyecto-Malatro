package cl.uchile.dcc
package pokerhands

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


/**
 * Represents an abstract poker hand based on rank repetition.
 *
 * This class provides shared functionality for hands that depend on
 * counting how many times each rank appears (e.g., Pair, Three of a Kind).
 *
 * It extends PokerHand and serves as a base for more specific combinations.
 */
abstract class RankBasedHand extends PokerHand {


  /**
   * Builds a frequency counter for the ranks in a set of cards.
   *
   * This method iterates through the given cards and counts how many times
   * each rank appears, using the rank's order as the key.
   *
   * @param cards the cards to evaluate
   * @return a map where keys are rank orders and values are their frequencies
   */
  def buildCounter(cards: ArrayBuffer[Card]): mutable.Map[Int, Int] = {
    val counter = mutable.Map[Int, Int]()

    for (card <- cards) {
      val ord = card.rank.order
      counter(ord) = counter.getOrElse(ord, 0) + 1
    }

    counter
  }
}

package cl.uchile.dcc
package pokerhands

import cl.uchile.dcc.jokers.Joker

import scala.collection.mutable.ArrayBuffer


/**
 * Represents the Pair poker hand.
 *
 * A Pair is formed when two cards share the same rank.
 *
 * This hand is one of the simplest combinations and is evaluated
 * by counting occurrences of each rank in the given set of cards.
 */
class Pair extends RankBasedHand {

  /**
   * The base number of chips awarded for a Pair.
   */
  override val basechips = 10

  /**
   * The multiplier applied to the base chips.
   */
  override val basemult = 2


  /**
   * Determines if a set of cards contains a Pair.
   *
   * This method counts how many times each rank appears and checks
   * whether any rank appears exactly twice.
   *
   * @param cards the cards to evaluate
   * @return true if there is at least one pair in the cards
   */
  def isPair(cards: ArrayBuffer[Card]): Boolean = {
    val counter = buildCounter(cards)

    counter.values.exists(_ == 2)
  }

  override def applyScore(score: Score, j: Joker): Score = {
    j.pairScore(score, this)
  }
}
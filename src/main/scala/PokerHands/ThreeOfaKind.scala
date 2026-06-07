package cl.uchile.dcc
package PokerHands

import Jokers._

import scala.collection.mutable.ArrayBuffer


/**
 * Represents the Three of a Kind poker hand.
 *
 * A Three of a Kind is formed when three cards share the same rank.
 *
 * This hand is evaluated by counting the occurrences of each rank
 * and checking if any rank appears exactly three times.
 */
class ThreeOfaKind extends RankBasedHand {

  /**
   * The base number of chips awarded for a Three of a Kind.
   */
  val basechips = 30

  /**
   * The multiplier applied to the base chips.
   */
  val basemult = 3


  /**
   * Determines if a set of cards contains a Three of a Kind.
   *
   * This method uses a rank frequency counter to check whether
   * any rank appears exactly three times.
   *
   * @param cards the cards to evaluate
   * @return true if there is a Three of a Kind in the cards
   */
  def isTOaK(cards: ArrayBuffer[Card]): Boolean = {
    val counter = buildCounter(cards)

    counter.values.exists(_ >= 3)
  }

  override def applyScore(score: Score, j: Joker): Score = {
    j.threeOfaKindScore(score, this)
    
  }
}


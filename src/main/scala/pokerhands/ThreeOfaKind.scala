package cl.uchile.dcc
package pokerhands

import jokers._

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

  /**
   * Applies the scoring effect of a Joker specifically to a Three of a Kind hand via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the Three of a Kind
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.threeOfaKindScore(score, this)
  }

  override def scoringCards(playedCards: ArrayBuffer[Card]): ArrayBuffer[Card] = {
    val groups = playedCards.groupBy(_.rank.value)
    val trioCards = groups.values.find(_.length == 3).get

    trioCards
  }
}
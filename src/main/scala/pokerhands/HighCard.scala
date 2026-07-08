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

  /**
   * Applies the scoring effect of a Joker specifically to a High Card hand via double dispatch.
   *
   * @param score the current score
   * @param j     the Joker being applied
   * @return the updated score after the joker evaluates the High Card
   */
  override def applyScore(score: Score, j: Joker): Score = {
    j.highCardScore(score, this)
  }

  /**
   * Extracts the highest-ranking card from the played cards to be used for scoring.
   *
   * @param playedCards the cards played by the user
   * @return an ArrayBuffer containing only the single highest card
   */
  override def scoringCards(playedCards: ArrayBuffer[Card]): ArrayBuffer[Card] = {
    val highestCard = ArrayBuffer(playedCards.maxBy(card => card.rank.value))

    highestCard
  }

  override def pokerHandName: String = "High Card"
}
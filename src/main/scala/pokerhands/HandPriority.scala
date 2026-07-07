package cl.uchile.dcc
package pokerhands

/**
 * Determines the highest-priority poker hand for a given set of cards.
 *
 * This class evaluates a collection of cards and selects the best possible
 * PokerHand according to a predefined priority order.
 *
 * The priority is evaluated from highest to lowest:
 * - Straight Flush
 * - Flush
 * - Straight
 * - Three of a Kind
 * - Pair
 * - High Card
 *
 * This class centralizes the decision logic and ensures that only the
 * strongest valid hand is returned.
 */
class HandPriority(val hand: Hand) {

  /**
   * Evaluates a set of cards and returns the best matching PokerHand.
   *
   * The method checks each hand type in descending order of priority,
   * returning the first match found.
   *
   * @return the highest-ranking PokerHand that applies to the given cards
   */
  def bestHand : PokerHand = {
    val cards = hand.handCards

    if (new StraightFlush().isStraightFlush(cards)) {
      new StraightFlush
    } else if (new Flush().isFlush(cards)) {
      new Flush
    } else if (new Straight().isStraight(cards)) {
      new Straight
    } else if (new ThreeOfaKind().isTOaK(cards)) {
      new ThreeOfaKind
    } else if (new Pair().isPair(cards)) {
      new Pair
    } else {
      new HighCard
    }
  }
}
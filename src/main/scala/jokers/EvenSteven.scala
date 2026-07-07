package cl.uchile.dcc
package jokers

import pokerhands.PokerHand

/**
 * Represents the Even Steven Joker.
 *
 * This Joker introduces a special effect that alters the behavior of the game
 * by rewarding the player for using even-ranked cards.
 */
class EvenSteven extends Joker {

  /**
   * The name of this Joker.
   *
   * Used to identify the Joker within the game.
   *
   * @return a string representing the name "Even Steven"
   */
  override def name: String = "Even Steven"

  /**
   * Recursively counts the number of even cards in a given list.
   *
   * @param cards the list of cards to evaluate
   * @return the total number of even cards
   */
  private def evenCounter(cards: List[Card]): Int = {
    cards match {
      case Nil => 0
      case head :: tail =>
        val isEvenValue = if (head.rank.isEven) 1 else 0
        isEvenValue + evenCounter(tail)
    }
  }

  /**
   * Applies the specific effect of the Even Steven Joker.
   *
   * Adds +4 to the score's multiplier for every even card present in the played cards.
   *
   * @param score the current score before the Joker's effect
   * @param hand  the poker hand being evaluated
   * @param cards the list of cards played
   * @return the updated score with the Joker's effect applied
   */
  override def effect(score: Score, hand: PokerHand, cards: List[Card]): Score = {
    hand match {
      case _ =>
        val evenCount = evenCounter(cards)
        if (evenCount > 0) {
          new Score(score.getChips, score.getMult + (4 * evenCount))
        } else {
          score
        }
    }
  }
}
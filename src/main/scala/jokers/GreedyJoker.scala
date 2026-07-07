package cl.uchile.dcc
package jokers

import pokerhands.PokerHand

/**
 * Represents the Greedy Joker.
 *
 * This Joker introduces a special effect that alters the behavior of the game
 * by rewarding the player for using Diamond-suited cards.
 */
class GreedyJoker extends Joker {

  /**
   * The name of this Joker.
   *
   * Used to identify the Joker within the game.
   *
   * @return a string representing the name "Greedy Joker"
   */
  override def name: String = "Greedy Joker"

  /**
   * Recursively counts the number of Diamond cards in a given list.
   *
   * @param cards the list of cards to evaluate
   * @return the total number of Diamond cards
   */
  private def diamondCounter(cards: List[Card]): Int = {
    cards match {
      case Nil => 0
      case head :: tail =>
        val isDiamondValue = if (head.suit.isDiamond) 1 else 0
        isDiamondValue + diamondCounter(tail)
    }
  }

  /**
   * Applies the specific effect of the Greedy Joker.
   *
   * Adds +3 to the score's multiplier for every Diamond card present in the played cards.
   *
   * @param score the current score before the Joker's effect
   * @param hand  the poker hand being evaluated
   * @param cards the list of cards played
   * @return the updated score with the Joker's effect applied
   */
  override def effect(score: Score, hand: PokerHand, cards: List[Card]): Score = {
    hand match {
      case _ =>
        val diamondCount = diamondCounter(cards)
        if (diamondCount > 0) {
          new Score(score.getChips, score.getMult + (3 * diamondCount))
        } else {
          score
        }
    }
  }
}
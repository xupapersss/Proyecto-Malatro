package cl.uchile.dcc
package jokers

import pokerhands.PokerHand

/**
 * Represents the Greedy Joker.
 *
 * This Joker is intended to introduce a special effect that alters
 * the behavior of the game in a non-trivial way.
 *
 * The exact effect is not yet implemented, but it is expected to
 * interact with hand evaluation or scoring logic.
 */
class GreedyJoker extends Joker {

  /**
   * The name of this Joker.
   *
   * Used to identify the Joker within the game.
   * * @return a string representing the name "Greedy Joker"
   */
  override def name: String = "Greedy Joker"

  /**
   * Applies the specific effect of the Greedy Joker.
   *
   * This method is intended to define how this particular Joker modifies the game,
   * such as altering scoring multipliers or chips.
   *
   * NOTE: This is not yet implemented.
   */

  /**
   * por cada diamante suma +3 al mult
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
   * The effect of the Joker.
   *
   * This method is intended to define how the Joker modifies the game,
   * such as altering scoring, changing card behavior, or affecting hand evaluation.
   *
   * NOTE: This is not yet implemented.
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
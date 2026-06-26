package cl.uchile.dcc
package jokers

import pokerhands.PokerHand

/**
 * Represents the Even Steven.
 *
 * This Joker is intended to introduce a special effect that alters
 * the behavior of the game in a non-trivial way.
 *
 * The exact effect is not yet implemented, but it is expected to
 * interact with hand evaluation or scoring logic.
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
   * Applies the specific effect of the Even Steven.
   *
   * This method is intended to define how this particular Joker modifies the game,
   * such as altering scoring multipliers or chips.
   *
   * NOTE: This is not yet implemented.
   */
  
  private def evenCounter(cards: List[Card]): Int = {
    cards match {
      case Nil => 0
      case head :: tail =>
        val isEvenValue = if (head.rank.isEven) 1 else 0
        isEvenValue + evenCounter(tail)
    }
  }
  
  override def effect() :Score = ???
  
  /**
   * por cada carta par, +4 de mult
   */

}
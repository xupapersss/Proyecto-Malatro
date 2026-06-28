package cl.uchile.dcc
package jokers

import pokerhands.{PokerHand, Straight, StraightFlush}

import cl.uchile.dcc.Score


/**
 * Represents the Devious Joker.
 *
 * This Joker is intended to introduce a special effect that alters
 * the behavior of the game in a non-trivial way.
 *
 * The exact effect is not yet implemented, but it is expected to
 * interact with hand evaluation or scoring logic.
 */
class DeviousJoker extends Joker {

  /**
   * The name of this Joker.
   *
   * Used to identify the Joker within the game.
   *
   * @return a string representing the name "Devious Joker"
   */
  override def name: String = "Devious Joker"
  
  /**
   * Applies the specific effect of the Devious Joker.
   *
   * This method is intended to define how this particular Joker modifies the game,
   * such as altering scoring multipliers or chips.
   *
   * NOTE: This is not yet implemented.
   */

  override def effect(score: Score, hand: PokerHand, cards: List[Card]): Score = {
    hand match {
      case _: Straight => new Score(score.getChips + 100, score.getMult)
      case _: StraightFlush => new Score(score.getChips + 100, score.getMult)
      case _ => score
    }
  }
}
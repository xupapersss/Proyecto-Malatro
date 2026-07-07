package cl.uchile.dcc
package jokers

import pokerhands.{PokerHand, Straight, StraightFlush}

import cl.uchile.dcc.Score

/**
 * Represents the Devious Joker.
 *
 * This Joker introduces a special effect that alters the behavior of the game
 * by modifying the scoring logic for specific hands.
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
   * Adds 100 to the base chips if the evaluated hand is a Straight or a Straight Flush.
   *
   * @param score the current score before the Joker's effect
   * @param hand  the poker hand being evaluated
   * @param cards the list of cards played
   * @return the updated score with the Joker's effect applied
   */
  override def effect(score: Score, hand: PokerHand, cards: List[Card]): Score = {
    hand match {
      case _: Straight => new Score(score.getChips + 100, score.getMult)
      case _: StraightFlush => new Score(score.getChips + 100, score.getMult)
      case _ => score
    }
  }
}
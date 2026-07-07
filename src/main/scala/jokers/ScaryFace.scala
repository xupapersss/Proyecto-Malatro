package cl.uchile.dcc
package jokers

import pokerhands.PokerHand

/**
 * Represents the Scary Face Joker.
 *
 * This Joker introduces a special effect that alters the behavior of the game
 * by rewarding the player for using face cards.
 */
class ScaryFace extends Joker {

  /**
   * The name of this Joker.
   *
   * Used to identify the Joker within the game.
   *
   * @return a string representing the name "Scary Face"
   */
  override def name: String = "Scary Face"

  /**
   * Recursively counts the number of face cards (Jack, Queen, King) in a given list.
   *
   * @param cards the list of cards to evaluate
   * @return the total number of face cards
   */
  private def faceCardCounter(cards: List[Card]) : Int = {
    cards match {
      case Nil => 0
      case head :: tail =>
        val isFaceCardValue = if (head.rank.isFaceCard) 1 else 0
        isFaceCardValue + faceCardCounter(tail)
    }
  }

  /**
   * Applies the specific effect of the Scary Face Joker.
   *
   * Adds +30 to the base chips for every face card present in the played cards.
   *
   * @param score the current score before the Joker's effect
   * @param hand  the poker hand being evaluated
   * @param cards the list of cards played
   * @return the updated score with the Joker's effect applied
   */
  override def effect(score: Score, hand: PokerHand, cards: List[Card]): Score = {
    hand match {
      case _ =>
        val faceCardCount = faceCardCounter(cards)
        if (faceCardCount > 0) {
          new Score(score.getChips + (30 * faceCardCount), score.getMult)
        } else {
          score
        }
    }
  }
}
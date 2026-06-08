package cl.uchile.dcc
import suit.*
import rank.*
import jokers.*

import scala.collection.mutable.ArrayBuffer

/**
 * Represents a playing card in the game.
 *
 * A Card is defined by two main components:
 * - A suit (e.g., Hearts, Spades, Clubs, Diamonds)
 * - A rank (e.g., Ace, Two, King)
 *
 * Cards are the fundamental elements used to form hands
 * and evaluate poker combinations.
 *
 * @param suit the suit of the card
 * @param rank the rank of the card
 */
class Card (val suit:Suit, val rank: Rank){

  /**
   * The chip value of the card.
   *
   * This value is derived directly from the rank of the card
   * and is used in score calculations.
   *
   * @return the chip value associated with the card's rank
   */
  def chips: Int = rank.value

  def applyScore(score: Score, jokers: ArrayBuffer[Joker]): Score ={
    var actualScore = score
    var i = 0

    while (i < jokers.length){
      val actualJoker = jokers(i)

      actualScore = rank.applyScore(actualScore, actualJoker)

      actualScore = suit.applyScore(actualScore,actualJoker)

      i += 1
    }
    actualScore
  }
}
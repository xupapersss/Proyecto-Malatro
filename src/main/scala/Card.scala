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
class Card (val rank: Rank, val suit:Suit) extends Scorable {

  /**
   * The chip value of the card.
   *
   * This value is derived directly from the rank of the card
   * and is used in score calculations.
   *
   * @return the chip value associated with the card's rank
   */
  def chips: Int = rank.value

  override def applyScore(score: Score, joker: ArrayBuffer[Joker]): Score ={
    var actualScore = score
    var i = 0
    
    while (i < joker.length){
      val actualJoker = joker(i)
      
      rank.applyScore(actualScore, actualJoker)
      
      suit.applyScore(actualScore,actualJoker)
      
      i += 1
    }
    actualScore
  }
}
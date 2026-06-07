package cl.uchile.dcc
package Suit
import Jokers._

/**
 * Represents the Spades suit.
 *
 * All cards of this suit share the same label: "Spades".
 */
class Spades extends Suit {
  override val label = "Spades"

  override def applyScore(score: Score, j: Joker): Score = {
    j.spadesScore(score, this)
  }
}

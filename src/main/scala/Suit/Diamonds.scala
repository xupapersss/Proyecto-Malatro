package cl.uchile.dcc
package Suit
import Jokers._

/**
 * Represents the Diamonds suit.
 *
 * All cards of this suit share the same label: "Diamonds".
 */
class Diamonds extends Suit {
  override val label = "Diamonds"

  override def applyScore(score: Score, j: Joker): Score = {
    j.diamondsScore(score, this)
  }
}

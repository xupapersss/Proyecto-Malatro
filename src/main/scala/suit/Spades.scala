package cl.uchile.dcc
package suit
import jokers._

import scala.collection.mutable.ArrayBuffer

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

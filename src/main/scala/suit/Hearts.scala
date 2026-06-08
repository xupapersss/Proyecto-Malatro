package cl.uchile.dcc
package suit
import jokers._

/**
 * Represents the Hearts suit.
 *
 * All cards of this suit share the same label: "Hearts".
 */
class Hearts extends Suit {
  override val label = "Hearts"

  override def applyScore(score: Score, j: Joker): Score = {
    j.heartsScore(score, this)
  }
    
}

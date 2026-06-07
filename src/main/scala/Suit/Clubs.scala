package cl.uchile.dcc
package Suit

import Jokers._

/**
 * Represents the Clubs suit.
 *
 * All cards of this suit share the same label: "Clubs".
 */
class Clubs extends Suit {
  override val label = "Clubs"

  override def applyScore(score: Score, j: Joker): Score = {
    j.clubsScore(score, this)
  }
}

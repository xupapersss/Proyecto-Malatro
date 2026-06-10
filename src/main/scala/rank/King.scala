package cl.uchile.dcc
package rank
import jokers._

/**
 * Represents the King card rank.
 *
 * This is the highest face card with a fixed scoring value.
 *
 * Category: Face Card
 */
class King extends FaceCard {
  val order: Int = 13
  val value: Int = 10

  override def applyScore(score: Score, j: Joker): Score = {
    j.kingScore(score, this)
  }
}

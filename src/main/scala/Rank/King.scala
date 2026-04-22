package cl.uchile.dcc
package Rank

/**
 * Represents the King card rank.
 *
 * This is the highest face card with a fixed scoring value.
 *
 * Category: Face Card
 */
class King extends Rank {
  val order: Int = 13
  val value: Int = 10
  override val category = "Face Card"
}

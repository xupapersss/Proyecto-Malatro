package cl.uchile.dcc
package Rank

/**
 * Represents the Jack card rank.
 *
 * This is a face card with a fixed scoring value and higher order.
 *
 * Category: Face Card
 */

class Jack extends Rank {
  val order: Int = 11
  val value: Int = 10
  override val category = "Face Card"
}

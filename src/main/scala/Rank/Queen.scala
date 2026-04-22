package cl.uchile.dcc
package Rank

/**
 * Represents the Queen card rank.
 *
 * This is a face card with a fixed scoring value and high order.
 *
 * Category: Face Card
 */

class Queen extends Rank {
  val order: Int = 12
  val value: Int = 10
  override val category = "Face Card"
}


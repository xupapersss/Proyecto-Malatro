package cl.uchile.dcc
package Rank

/**
 * Represents the Five card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */
class Five extends Rank {
  val order: Int = 5
  val value: Int = 5
  override val category = "Odd"
}

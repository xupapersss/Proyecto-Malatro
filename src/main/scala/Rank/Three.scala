package cl.uchile.dcc
package Rank

/**
 * Represents the Three card rank.
 *
 * This is a numeric card used in sequences and scoring.
 *
 * Category: Odd
 */

class Three extends Rank {
  val order: Int = 3
  val value: Int = 3
  override val category = "Odd"
}


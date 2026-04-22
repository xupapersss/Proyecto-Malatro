package cl.uchile.dcc
package Jokers

/**
 * Represents a Joker card with special behavior in the game.
 *
 * A Joker introduces custom effects that can modify how a hand is evaluated,
 * how scores are calculated, or how cards interact with each other.
 *
 * Each Joker has:
 * - a name, used to identify it
 * - an effect, which defines its behavior in the game
 */

trait Joker {

  /**
   * The name of the Joker.
   *
   * This is used to identify the Joker and distinguish it from others.
   */

  def name : String

  /**
   * The effect of the Joker.
   *
   * This method is intended to define how the Joker modifies the game,
   * such as altering scoring, changing card behavior, or affecting hand evaluation.
   *
   * NOTE: This is not yet implemented.
   */

  def effect = ???
}

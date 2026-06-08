package cl.uchile.dcc
package jokers

/**
 * Represents the Even Steven.
 *
 * This Joker is intended to introduce a special effect that alters
 * the behavior of the game in a non-trivial way.
 *
 * The exact effect is not yet implemented, but it is expected to
 * interact with hand evaluation or scoring logic.
 */
class EvenSteven extends Joker {

  /**
   * The name of this Joker.
   *
   * Used to identify the Joker within the game.
   */
  override def name: String = "Even Steven"

  override def effect(): Unit = {}
}

package cl.uchile.dcc
package jokers

/**
 * Represents the Scary Face.
 *
 * This Joker is intended to introduce a special effect that alters
 * the behavior of the game in a non-trivial way.
 *
 * The exact effect is not yet implemented, but it is expected to
 * interact with hand evaluation or scoring logic.
 */
class ScaryFace extends Joker {

  /**
   * The name of this Joker.
   *
   * Used to identify the Joker within the game.
   *
   * @return a string representing the name "Scary Face"
   */
  override def name: String = "Scary Face"

  /**
   * Applies the specific effect of the Scary Face.
   *
   * This method is intended to define how this particular Joker modifies the game,
   * such as altering scoring multipliers or chips.
   *
   * NOTE: This is not yet implemented.
   */
  override def effect(): Score = ???
  /**
   * por cada facecard añade +30 chips
   */
}
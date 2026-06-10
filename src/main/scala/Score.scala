package cl.uchile.dcc

/**
 * Represents the score obtained from a hand.
 *
 * A Score is calculated based on two components:
 * - chips: the base value obtained from the cards
 * - mult:  a multiplier applied to the chips
 *
 * The final score is the product of these two values.
 *
 * @param chips the base chip value of the hand
 * @param mult  the multiplier applied to the chips
 */
class Score (chips: Int, mult: Int) {

  /**
   * Computes the total score.
   *
   * @return the product of chips and multiplier
   */
  def total:Int = {
    chips * mult
  }

  /**
   * Returns a formatted string representation of the score.
   *
   * @return a string showing the total score
   */
  def printScore(): String = {
    s"Total Score = $total"
  }
}
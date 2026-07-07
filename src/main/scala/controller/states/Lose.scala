package cl.uchile.dcc
package controller.states

/**
 * State representing a game over (defeat) condition.
 *
 * This state is reached when the player uses all of their available plays
 * but fails to reach the target score.
 */
class Lose extends GameState {

  override def isGameOver: Boolean = true
}
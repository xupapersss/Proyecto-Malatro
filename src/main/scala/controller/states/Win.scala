package cl.uchile.dcc
package controller.states

/**
 * State representing a victorious game over condition.
 *
 * This state is reached when the player successfully matches or exceeds
 * the target score within the allowed number of plays.
 */
class Win extends GameState {

  override def isWin: Boolean = true

  override def isGameOver: Boolean = true
}
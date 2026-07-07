package cl.uchile.dcc
package controller.states

import controller.Controller

/**
 * State representing the initial setup phase of the game.
 *
 * This is the starting state of the controller. It only allows the `start`
 * action to begin the game loop.
 */
class InitialSetup extends GameState {
  override def start(context: Controller): Unit = context.setState(new PlayersTurn)
}
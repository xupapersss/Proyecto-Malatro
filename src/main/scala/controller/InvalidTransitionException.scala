package cl.uchile.dcc
package controller

import controller.states.GameState

/**
 * Exception thrown when an invalid action is attempted within a specific game state.
 *
 * @param action the name of the attempted action (e.g., "play", "discard")
 * @param state  the current GameState where the action is not allowed
 */
class InvalidTransitionException(action : String, state: GameState)
  extends Exception(
    s"Invalid transition: cannot '$action' when in state '$state'"
  )
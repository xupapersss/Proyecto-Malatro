package cl.uchile.dcc
package controller

import controller.states.GameState

class InvalidTransitionException(action : String, state: GameState)
  extends Exception(
    s"Invalid transition: cannot '$action' when in state '$state'"
  )

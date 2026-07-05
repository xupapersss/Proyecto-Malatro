package cl.uchile.dcc
package controller.states

import controller.Controller

trait GameState protected() {
  def updateState(context: Controller, state: GameState) : Unit =
    context.setState(state)
}

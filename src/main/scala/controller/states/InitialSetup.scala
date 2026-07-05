package cl.uchile.dcc
package controller.states

import controller.Controller

class InitialSetup extends GameState {
  override def start(context: Controller): Unit = context.setState(new PlayersTurn)
}

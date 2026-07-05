package cl.uchile.dcc
package controller.states

import controller.Controller

class PlayersTurn extends GameState {

  override def playCards(context: Controller): Unit =
    context.setState(new EvaluatingState)

  override def discardCards(context: Controller): Unit =
    context.setState(new PlayersTurn)
}

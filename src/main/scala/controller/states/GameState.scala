package cl.uchile.dcc
package controller.states

import controller.{Controller, InvalidTransitionException}

trait GameState protected() {
  def updateState(context: Controller, state: GameState) : Unit =
    context.setState(state)

  def start(context: Controller): Unit =
    error("start")

  def playCards(context: Controller): Unit =
    error("play")

  def discardCards(context: Controller): Unit =
    error("discard")

  def evaluateScore(context: Controller): Unit =
    error("evaluate")

  private def error(action: String): Nothing =
    throw InvalidTransitionException(action,"placeholder")
}

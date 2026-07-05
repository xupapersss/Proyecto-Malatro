package cl.uchile.dcc
package controller.states

import controller.{Controller, InvalidTransitionException}

import scala.collection.mutable.ArrayBuffer

trait GameState protected() {
  def updateState(context: Controller, state: GameState) : Unit =
    context.setState(state)

  def start(context: Controller): Unit =
    error("start")

  def playCards(context: Controller, indexes: ArrayBuffer[Int]): Unit =
    error("play")

  def discardCards(context: Controller, indexes: ArrayBuffer[Int]): Unit =
    error("discard")

  def evaluateScore(context: Controller): Unit =
    error("evaluate")

  private def error(action: String): Nothing =
    throw InvalidTransitionException(action,"placeholder")
}

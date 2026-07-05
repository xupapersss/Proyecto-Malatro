package cl.uchile.dcc
package controller.states

import controller.{Controller, InvalidTransitionException}

import scala.collection.mutable.ArrayBuffer

trait GameState protected() {

  def start(context: Controller): Unit =
    error("start")

  def playCards(context: Controller, indexes: ArrayBuffer[Int]): Unit =
    error("play")

  def discardCards(context: Controller, indexes: ArrayBuffer[Int]): Unit =
    error("discard")

  def evaluateScore(context: Controller): Unit =
    error("evaluate")
    
  def isGameOver: Boolean = false
    
  def isWin: Boolean = false

  private def error(action: String): Nothing =
    throw InvalidTransitionException(action,Controller().getState)
}

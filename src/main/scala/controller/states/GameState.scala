package cl.uchile.dcc
package controller.states

import controller.{Controller, InvalidTransitionException}

import scala.collection.mutable.ArrayBuffer

/**
 * Base trait for the states in the Controller's State machine.
 *
 * Defines the default behavior for all game actions. By default,
 * attempting an action in a state that does not support it will trigger
 * an InvalidTransitionException. Concrete states must override the allowed methods.
 */
trait GameState protected() {

  /** Transitions the game from the initial setup to the active playing phase. */
  def start(context: Controller): Unit =
    error("start")

  /** Plays the selected cards and evaluates their score. */
  def playCards(context: Controller, indexes: ArrayBuffer[Int]): Unit =
    error("play")

  /** Discards the selected cards from the hand. */
  def discardCards(context: Controller, indexes: ArrayBuffer[Int]): Unit =
    error("discard")

  /** Evaluates the current score against the target to determine win/lose conditions. */
  def evaluateScore(context: Controller): Unit =
    error("evaluate")

  /** Indicates whether the game has reached an end state (Win or Lose). */
  def isGameOver: Boolean = false

  /** Indicates whether the game has been won. */
  def isWin: Boolean = false

  /** Throws an exception indicating that the action is not allowed in the current state. */
  private def error(action: String): Nothing =
    throw InvalidTransitionException(action,Controller().getState)
}

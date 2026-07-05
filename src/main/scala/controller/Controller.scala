package cl.uchile.dcc
package controller

import controller.states.{GameState, InitialSetup}

import scala.collection.mutable.ArrayBuffer

class Controller {
  var hand: Hand = _
  var currentScore: Int = 0
  var targetScore: Int = 0
  
  
  
  
  
  private var state: GameState = new InitialSetup
  def setState(state: GameState) : Unit =
    this.state = state

  private def safeRun(op: () => Unit): Unit =
    try op()
    catch {
      case e: InvalidTransitionException =>
        println(e.getMessage)
    }

  def start(): Unit =
    safeRun(() => state.start(this))

  def playCards(indexes: ArrayBuffer[Int]): Unit =
    safeRun(() => state.playCards(this, indexes))


  def discardCards(indexes: ArrayBuffer[Int]): Unit =
    safeRun(() => state.discardCards(this, indexes))

  def evaluateScore(): Unit =
    safeRun(() => state.evaluateScore(this))

}

package cl.uchile.dcc
package controller

import controller.states.{GameState, InitialSetup}

class Controller {
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

  def playCards(): Unit =
    safeRun(() => state.playCards(this))


  def discardCards(): Unit =
    safeRun(() => state.discardCards(this))

  def evaluateScore(): Unit =
    safeRun(() => state.evaluateScore(this))

}

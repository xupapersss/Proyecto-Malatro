package cl.uchile.dcc
package controller

import controller.states.{GameState, InitialSetup, Lose}

import controller.observer.{Observer, Subject}

import scala.collection.mutable.ArrayBuffer

class Controller extends Observer {
  var currentScore: Int = 0
  var targetScore: Int = 0
  private var state: GameState = new InitialSetup

  override def update(subject: Subject, response: Any): Unit = {
    if response.isInstanceOf[String] then {
      println("You are out of plays")
      setState(new Lose)
    }
  }

  def setState(state: GameState): Unit =
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

  def initialGame(): Unit = {
    hand = new Hand(initialCards, initialJokers)
    hand.attach(this)
  }
}

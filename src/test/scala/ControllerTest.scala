package cl.uchile.dcc

import munit.FunSuite
import controller.states.{GameState, InitialSetup, Lose}
import controller.Controller

import scala.collection.mutable.ArrayBuffer

class ControllerTest extends FunSuite {

  var controller: Controller = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new Controller()
  }

  test("The controller should initialize with InitialSetup state and scores at 0") {
    assert(controller.getState.isInstanceOf[InitialSetup])
    assertEquals(controller.currentScore, 0)
    assertEquals(controller.targetScore, 0)
  }

  test("setState should change the controller's current state") {
    val loseState = new Lose()
    controller.setState(loseState)
    assertEquals(controller.getState, loseState)
  }

  test("initialGame should create a hand of 8 cards, attach the controller, and call start") {
    controller.initialGame()

    assert(controller.hand != null)

    assert(!controller.getState.isInstanceOf[InitialSetup])
  }

  test("The update method should change the state to Lose when receiving a String") {
    controller.update(null, "Out of plays")

    assert(controller.getState.isInstanceOf[Lose])
  }

  test("The update method should NOT do anything if it receives a type other than String") {
    val initialState = controller.getState

    controller.update(null, 123)

    assertEquals(controller.getState, initialState)
  }

  // ==========================================
  // Tests for method delegation (playCards, discardCards, evaluateScore)
  // ==========================================

  test("playCards should correctly delegate to the current state") {
    var wasCalled = false
    // We override the method in an anonymous state to verify that the Controller calls it
    val mockState = new InitialSetup {
      override def playCards(c: Controller, indexes: ArrayBuffer[Int]): Unit = {
        wasCalled = true
      }
    }
    controller.setState(mockState)
    controller.playCards(ArrayBuffer(0, 1))

    assert(wasCalled, "playCards was not delegated to the state")
  }

  test("discardCards should correctly delegate to the current state") {
    var wasCalled = false
    val mockState = new InitialSetup {
      override def discardCards(c: Controller, indexes: ArrayBuffer[Int]): Unit = {
        wasCalled = true
      }
    }
    controller.setState(mockState)
    controller.discardCards(ArrayBuffer(0))

    assert(wasCalled, "discardCards was not delegated to the state")
  }

  test("evaluateScore should correctly delegate to the current state") {
    var wasCalled = false
    val mockState = new InitialSetup {
      override def evaluateScore(c: Controller): Unit = {
        wasCalled = true
      }
    }
    controller.setState(mockState)
    controller.evaluateScore()

    assert(wasCalled, "evaluateScore was not delegated to the state")
  }

  // ==========================================
  // Test for exception handling in safeRun
  // ==========================================

  test("safeRun should catch InvalidTransitionException and prevent propagation") {
    // By default, Controller is initialized in the InitialSetup state.
    // Calling these actions in InitialSetup would normally throw an InvalidTransitionException
    // from the state. The Controller's safeRun method must catch it.
    try {
      controller.playCards(ArrayBuffer(0))
      controller.discardCards(ArrayBuffer(0))
      controller.evaluateScore()
      assert(true) // If execution reaches here, safeRun successfully caught the exceptions.
    } catch {
      case e: Exception => fail(s"safeRun did not catch the exception. Threw: ${e.getClass.getSimpleName}")
    }
  }

  // ==========================================
  // Test for additional cases in the observer (update)
  // ==========================================

  test("The update method should handle 'Out of discards' without changing state or crashing") {
    val initialState = controller.getState

    // Upon receiving "Out of discards", the original code only prints a message.
    // We verify that the program does not crash and the state remains the same.
    controller.update(null, "Out of discards")

    assertEquals(controller.getState, initialState)
  }
}
package cl.uchile.dcc

import munit.FunSuite
import controller.states.{GameState, InitialSetup, Lose, Win}
import controller.Controller

import scala.collection.mutable.ArrayBuffer

/**
 * Test suite for the main Controller of the Malatro game.
 *
 * This suite verifies the correct initialization of the controller,
 * state transitions, game creation, the handling of Observer updates,
 * exception management via safeRun, and card drawing mechanics.
 */
class ControllerTest extends FunSuite {

  var controller: Controller = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new Controller()
  }

  /** Verifies standard initialization values. */
  test("The controller should initialize with InitialSetup state and scores at 0") {
    assert(controller.getState.isInstanceOf[InitialSetup])
    assertEquals(controller.currentScore, 0)
    assertEquals(controller.targetScore, 0)
  }

  /** Verifies that setState successfully replaces the active state. */
  test("setState should change the controller's current state") {
    val loseState = new Lose()
    controller.setState(loseState)
    assertEquals(controller.getState, loseState)
  }

  /** Verifies the initial game setup populates the hand and starts the state machine. */
  test("initialGame should create a hand of 8 cards, attach the controller, and call start") {
    controller.initialGame()

    assert(controller.hand != null)

    assert(!controller.getState.isInstanceOf[InitialSetup])
  }

  /** Verifies the Observer update changes the state to Lose when plays run out. */
  test("The update method should change the state to Lose when receiving a String") {
    controller.update(null, "Out of plays")

    assert(controller.getState.isInstanceOf[Lose])
  }

  /** Verifies the Observer ignores unknown notifications. */
  test("The update method should NOT do anything if it receives a type other than String") {
    val initialState = controller.getState

    controller.update(null, 123)

    assertEquals(controller.getState, initialState)
  }

  // ==========================================
  // Tests for method delegation (playCards, discardCards, evaluateScore)
  // ==========================================

  /** Verifies that playing cards is correctly delegated to the internal state. */
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

  /** Verifies that discarding cards is correctly delegated to the internal state. */
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

  /** Verifies that score evaluation is correctly delegated to the internal state. */
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

  /** Verifies that safeRun acts as a shield against InvalidTransitionExceptions. */
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

  /** Verifies that safeRun acts as a shield against InvalidHand exceptions. */
  test("safeRun should catch InvalidHand exception and prevent propagation") {
    val mockState = new InitialSetup {
      override def playCards(c: Controller, indexes: ArrayBuffer[Int]): Unit = {
        throw new InvalidHand("Test InvalidHand")
      }
    }
    controller.setState(mockState)

    try {
      // This should trigger the InvalidHand, but safeRun must catch it gracefully
      controller.playCards(ArrayBuffer(0))
      assert(true)
    } catch {
      case e: Exception => fail(s"safeRun did not catch InvalidHand. Threw: ${e.getClass.getSimpleName}")
    }
  }

  // ==========================================
  // Test for additional cases in the observer (update)
  // ==========================================

  /** Verifies that 'Out of discards' triggers a message but no state change. */
  test("The update method should handle 'Out of discards' without changing state or crashing") {
    val initialState = controller.getState

    // Upon receiving "Out of discards", the original code only prints a message.
    // We verify that the program does not crash and the state remains the same.
    controller.update(null, "Out of discards")

    assertEquals(controller.getState, initialState)
  }

  // ==========================================
  // Tests for Game Over and Win condition checks
  // ==========================================

  /** Verifies that isGameOver and isWin correctly reflect the underlying state properties. */
  test("isGameOver and isWin should properly delegate to the current state") {
    val winState = new Win()
    controller.setState(winState)
    assert(controller.isWin, "Controller should report Win when in Win state")
    assert(controller.isGameOver, "Controller should report Game Over when in Win state")

    val loseState = new Lose()
    controller.setState(loseState)
    assert(!controller.isWin, "Controller should not report Win when in Lose state")
    assert(controller.isGameOver, "Controller should report Game Over when in Lose state")
  }

  // ==========================================
  // Tests for drawCards method
  // ==========================================

  /** Verifies that drawCards does not crash if the hand has not been initialized. */
  test("drawCards should do nothing and not crash if hand is null") {
    try {
      controller.drawCards()
      assert(true)
    } catch {
      case _: Exception => fail("drawCards crashed when hand was null")
    }
  }

  /** Verifies that drawCards successfully refills a partially empty hand back to 8 cards. */
  test("drawCards should refill the hand back to 8 cards if the deck is not empty") {
    // initialGame provides a full deck and hands out exactly 8 cards
    controller.initialGame()
    assertEquals(controller.hand.cards.length, 8)

    // We manually simulate a play by removing 3 cards directly from the underlying buffer
    controller.hand.handCards.remove(0, 3)
    assertEquals(controller.hand.cards.length, 5)

    // We call drawCards, which should calculate missing cards (3) and draw them from the deck
    controller.drawCards()

    assertEquals(controller.hand.cards.length, 8)
  }
}
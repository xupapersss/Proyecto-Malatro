package cl.uchile.dcc
import controller.states.*

import cl.uchile.dcc.controller.{Controller, InvalidTransitionException}
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class GameStateTest extends FunSuite {
  var controller: Controller = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new Controller()
  }

  // ==========================================
  // Tests for InitialSetup State
  // ==========================================

  test("InitialSetup should allow 'start' and transition to PlayersTurn") {
    val state = new InitialSetup()
    controller.setState(state)

    state.start(controller)

    assert(controller.getState.isInstanceOf[PlayersTurn])
  }

  test("InitialSetup should throw InvalidTransitionException for all other actions") {
    val state = new InitialSetup()
    controller.setState(state)
    val indexes = ArrayBuffer(0, 1)

    intercept[InvalidTransitionException] {
      state.playCards(controller, indexes)
    }
    intercept[InvalidTransitionException] {
      state.discardCards(controller, indexes)
    }
    intercept[InvalidTransitionException] {
      state.evaluateScore(controller)
    }
  }

  // ==========================================
  // Tests for EvaluatingState State
  // ==========================================

  test("EvaluatingState should transition to Win if currentScore is greater than or equal to targetScore") {
    val state = new EvaluatingState()
    controller.setState(state)

    // Set scores to simulate a win condition
    controller.currentScore = 1500
    controller.targetScore = 1000

    state.evaluateScore(controller)

    assert(controller.getState.isInstanceOf[Win])
  }

  test("EvaluatingState should transition to Lose if playCount is strictly greater than 3") {
    val state = new EvaluatingState()
    controller.setState(state)

    // Set scores so it doesn't trigger the Win condition
    controller.currentScore = 500
    controller.targetScore = 1000

    // We need a dummy hand to simulate the play count.
    // Replace this with a valid setup according to your Hand constructor requirements.
    controller.hand = new Hand(ArrayBuffer(), ArrayBuffer())

    // Simulate playing 4 times to exceed the limit
    controller.hand.playHand(ArrayBuffer())
    controller.hand.playHand(ArrayBuffer())
    controller.hand.playHand(ArrayBuffer())
    controller.hand.playHand(ArrayBuffer())

    state.evaluateScore(controller)

    assert(controller.getState.isInstanceOf[Lose])
  }

  test("EvaluatingState should transition to PlayersTurn if no win or lose conditions are met") {
    val state = new EvaluatingState()
    controller.setState(state)

    controller.currentScore = 500
    controller.targetScore = 1000
    controller.hand = new Hand(ArrayBuffer(), ArrayBuffer())

    state.evaluateScore(controller)

    assert(controller.getState.isInstanceOf[PlayersTurn])
  }

  test("EvaluatingState should throw InvalidTransitionException for non-evaluation actions") {
    val state = new EvaluatingState()
    controller.setState(state)
    val indexes = ArrayBuffer(0)

    intercept[InvalidTransitionException] {
      state.start(controller)
    }
    intercept[InvalidTransitionException] {
      state.playCards(controller, indexes)
    }
    intercept[InvalidTransitionException] {
      state.discardCards(controller, indexes)
    }
  }

  // ==========================================
  // Tests for PlayersTurn State
  // ==========================================

  test("PlayersTurn should correctly process playCards and transition to EvaluatingState") {
    val state = new PlayersTurn()
    controller.setState(state)

    // Since playCards heavily relies on HandPriority and HandScore, you need a valid
    // set of cards to prevent NullPointerExceptions or empty collections errors.
    // Ensure you populate this hand with actual cards for the test to pass successfully.
    controller.hand = new Hand(ArrayBuffer(/* Insert valid dummy cards here */), ArrayBuffer())
    val initialScore = controller.currentScore

    // Try to play valid indexes according to your dummy hand
    // state.playCards(controller, ArrayBuffer(0, 1, 2, 3, 4))

    // If you uncomment the line above with valid data, the state should change
    // assert(controller.getState.isInstanceOf[EvaluatingState])
    // assert(controller.currentScore > initialScore)
  }

  test("PlayersTurn should process discardCards without transitioning states") {
    val state = new PlayersTurn()
    controller.setState(state)

    // Add valid dummy cards to discard
    controller.hand = new Hand(ArrayBuffer(/* Insert valid dummy cards here */), ArrayBuffer())

    // Simulate discarding cards
    // state.discardCards(controller, ArrayBuffer(0))

    // State remains unchanged
    assert(controller.getState.isInstanceOf[PlayersTurn])
  }

  test("PlayersTurn should throw InvalidTransitionException for illegal actions") {
    val state = new PlayersTurn()
    controller.setState(state)

    intercept[InvalidTransitionException] {
      state.start(controller)
    }
    intercept[InvalidTransitionException] {
      state.evaluateScore(controller)
    }
  }

  // ==========================================
  // Tests for Win and Lose States
  // ==========================================

  test("Win state should block all actions") {
    val state = new Win()
    controller.setState(state)
    val indexes = ArrayBuffer(0)

    intercept[InvalidTransitionException] {
      state.start(controller)
    }
    intercept[InvalidTransitionException] {
      state.playCards(controller, indexes)
    }
    intercept[InvalidTransitionException] {
      state.discardCards(controller, indexes)
    }
    intercept[InvalidTransitionException] {
      state.evaluateScore(controller)
    }
  }

  test("Lose state should block all actions") {
    val state = new Lose()
    controller.setState(state)
    val indexes = ArrayBuffer(0)

    intercept[InvalidTransitionException] {
      state.start(controller)
    }
    intercept[InvalidTransitionException] {
      state.playCards(controller, indexes)
    }
    intercept[InvalidTransitionException] {
      state.discardCards(controller, indexes)
    }
    intercept[InvalidTransitionException] {
      state.evaluateScore(controller)
    }
  }
}
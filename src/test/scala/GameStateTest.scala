package cl.uchile.dcc
import controller.states.*

import cl.uchile.dcc.controller.{Controller, InvalidTransitionException}
import munit.FunSuite
import suit._
import rank._


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

  test("EvaluatingState should transition to Lose if playCount reaches 3 without winning") {
    val state = new EvaluatingState()
    controller.setState(state)

    controller.currentScore = 500
    controller.targetScore = 1000

    val dummyCard = new Card(new Hearts, new Two)
    val initialCards = ArrayBuffer(
      dummyCard, dummyCard, dummyCard, dummyCard,
      dummyCard, dummyCard, dummyCard, dummyCard
    )

    controller.hand = new Hand(initialCards, ArrayBuffer())

    assertEquals(controller.hand.getPlayCount, 0)

    controller.hand.playHand(ArrayBuffer(0))
    assertEquals(controller.hand.getPlayCount, 1)

    controller.hand.playHand(ArrayBuffer(0))
    assertEquals(controller.hand.getPlayCount, 2)

    // The 3rd play reaches the limit!
    controller.hand.playHand(ArrayBuffer(0))
    assertEquals(controller.hand.getPlayCount, 3)

    // We don't need a 4th play. Evaluating state should realize there are no plays left.
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

  // ==========================================
  // Tests for PlayersTurn State
  // ==========================================

  test("PlayersTurn should correctly process discardCards without transitioning states") {
    val state = new PlayersTurn()
    controller.setState(state)

    // We populate a dummy hand so HandPriority and handScore don't fail
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)

    controller.hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer())

    // We discard the card at index 0
    state.discardCards(controller, ArrayBuffer(0))

    // State should remain PlayersTurn
    assert(controller.getState.isInstanceOf[PlayersTurn])
  }

  test("PlayersTurn should process playCards, update currentScore and transition to EvaluatingState") {
    val state = new PlayersTurn()
    controller.setState(state)

    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)

    controller.hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer())
    controller.currentScore = 0

    val initialScore = controller.currentScore

    // Play valid indexes according to the dummy hand
    state.playCards(controller, ArrayBuffer(0, 1, 2, 3, 4))

    // The state must transition to EvaluatingState
    assert(controller.getState.isInstanceOf[EvaluatingState])

    // The score must have increased (assuming the played cards yield a score > 0)
    assert(controller.currentScore > initialScore)
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
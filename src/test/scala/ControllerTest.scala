package cl.uchile.dcc

import munit.FunSuite
import controller.states.{GameState, InitialSetup, Lose}
import controller.Controller

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
}
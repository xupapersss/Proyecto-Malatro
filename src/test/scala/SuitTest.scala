package cl.uchile.dcc
import munit.FunSuite
import suit._

/**
 * Test suite for the Suit classes.
 *
 * This suite ensures that all basic suits (Clubs, Diamonds, Hearts, Spades)
 * can be instantiated and that they act as distinct objects within the game.
 */
class SuitTest extends FunSuite {

  /** Verifies the correct instantiation of the Clubs suit. */
  test("Clubs can be created") {
    val suit = new Clubs
    assert(suit != null)
  }

  /** Verifies the correct instantiation of the Diamonds suit. */
  test("Diamonds can be created") {
    val suit = new Diamonds
    assert(suit != null)
  }

  /** Verifies the correct instantiation of the Hearts suit. */
  test("Hearts can be created") {
    val suit = new Hearts
    assert(suit != null)
  }

  /** Verifies the correct instantiation of the Spades suit. */
  test("Spades can be created") {
    val suit = new Spades
    assert(suit != null)
  }

  /** Verifies that all instances from different suit classes are distinct from one another. */
  test("Different suits are distinguishable") {
    val clubs = new Clubs
    val diamonds = new Diamonds
    val hearts = new Hearts
    val spades = new Spades

    assert(clubs != diamonds)
    assert(clubs != hearts)
    assert(clubs != spades)
    assert(diamonds != hearts)
    assert(diamonds != spades)
    assert(hearts != spades)
  }
}
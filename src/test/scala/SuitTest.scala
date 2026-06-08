package cl.uchile.dcc
import munit.FunSuite
import suit._

class SuitTest extends FunSuite {

  test("Clubs can be created") {
    val suit = new Clubs
    assert(suit != null)
  }

  test("Diamonds can be created") {
    val suit = new Diamonds
    assert(suit != null)
  }

  test("Hearts can be created") {
    val suit = new Hearts
    assert(suit != null)
  }

  test("Spades can be created") {
    val suit = new Spades
    assert(suit != null)
  }

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

package cl.uchile.dcc
import munit.FunSuite
import Jokers._


class JokerTest extends FunSuite {

  test("A GreedyJoker can be created") {
    val joker = new GreedyJoker
    assert(joker != null)
  }

  test("Different jokers are distinguishable") {
    val greedy = new GreedyJoker
    val devious = new DeviousJoker
    val even = new EvenSteven
    val scary = new ScaryFace

    assert(greedy != devious)
    assert(greedy != even)
    assert(greedy != scary)
    assert(devious != even)
    assert(devious != scary)
    assert(even != scary)
  }
}
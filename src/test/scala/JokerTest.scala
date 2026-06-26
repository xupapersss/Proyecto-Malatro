package cl.uchile.dcc
import munit.FunSuite
import jokers.*


class JokerTest extends FunSuite {

  test("Jokers can be created") {
    val greedy = new GreedyJoker
    val devious = new DeviousJoker
    val even = new EvenSteven
    val scary = new ScaryFace

    assert(greedy != null)
    assert(devious != null)
    assert(even != null)
    assert(scary != null)
  }

  test("Jokers have correct names") {
    assertEquals(new GreedyJoker().name, "Greedy Joker")
    assertEquals(new DeviousJoker().name, "Devious Joker")
    assertEquals(new EvenSteven().name, "Even Steven")
    assertEquals(new ScaryFace().name, "Scary Face")
  }

  test("Joker effects can be executed") {
    new GreedyJoker().effect()
    new DeviousJoker().effect()
    new EvenSteven().effect()
    new ScaryFace().effect()
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
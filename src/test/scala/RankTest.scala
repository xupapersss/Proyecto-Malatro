package cl.uchile.dcc
import munit.FunSuite
import Rank._


class RankTest extends FunSuite {

  test("An Ace can be created") {
    val rank = new Ace
    assert(rank != null)
  }

  test("A Two can be created") {
    val rank = new Two
    assert(rank != null)
  }

  test("A Three can be created") {
    val rank = new Three
    assert(rank != null)
  }

  test("A Four can be created") {
    val rank = new Four
    assert(rank != null)
  }

  test("A Five can be created") {
    val rank = new Five
    assert(rank != null)
  }

  test("A Six can be created") {
    val rank = new Six
    assert(rank != null)
  }

  test("A Seven can be created") {
    val rank = new Seven
    assert(rank != null)
  }

  test("An Eight can be created") {
    val rank = new Eight
    assert(rank != null)
  }

  test("A Nine can be created") {
    val rank = new Nine
    assert(rank != null)
  }

  test("A Ten can be created") {
    val rank = new Ten
    assert(rank != null)
  }

  test("A Jack can be created") {
    val rank = new Jack
    assert(rank != null)
  }

  test("A Queen can be created") {
    val rank = new Queen
    assert(rank != null)
  }

  test("A King can be created") {
    val rank = new King
    assert(rank != null)
  }

  test("Ace has correct order and value") {
    val rank = new Ace
    assertEquals(rank.order, 1)
    assertEquals(rank.value, 11)
  }

  test("Two has correct order and value") {
    val rank = new Two
    assertEquals(rank.order, 2)
    assertEquals(rank.value, 2)
  }

  test("Three has correct order and value") {
    val rank = new Three
    assertEquals(rank.order, 3)
    assertEquals(rank.value, 3)
  }

  test("Four has correct order and value") {
    val rank = new Four
    assertEquals(rank.order, 4)
    assertEquals(rank.value, 4)
  }

  test("Five has correct order and value") {
    val rank = new Five
    assertEquals(rank.order, 5)
    assertEquals(rank.value, 5)
  }

  test("Six has correct order and value") {
    val rank = new Six
    assertEquals(rank.order, 6)
    assertEquals(rank.value, 6)
  }

  test("Seven has correct order and value") {
    val rank = new Seven
    assertEquals(rank.order, 7)
    assertEquals(rank.value, 7)
  }

  test("Eight has correct order and value") {
    val rank = new Eight
    assertEquals(rank.order, 8)
    assertEquals(rank.value, 8)
  }

  test("Nine has correct order and value") {
    val rank = new Nine
    assertEquals(rank.order, 9)
    assertEquals(rank.value, 9)
  }

  test("Ten has correct order and value") {
    val rank = new Ten
    assertEquals(rank.order, 10)
    assertEquals(rank.value, 10)
  }

  test("Jack has correct order and value") {
    val rank = new Jack
    assertEquals(rank.order, 11)
    assertEquals(rank.value, 10)
  }

  test("Queen has correct order and value") {
    val rank = new Queen
    assertEquals(rank.order, 12)
    assertEquals(rank.value, 10)
  }

  test("King has correct order and value") {
    val rank = new King
    assertEquals(rank.order, 13)
    assertEquals(rank.value, 10)
  }

  test("Different ranks are distinguishable") {
    val ace = new Ace
    val two = new Two
    val jack = new Jack
    val king = new King

    assert(ace != two)
    assert(ace != jack)
    assert(ace != king)
    assert(two != jack)
    assert(two != king)
    assert(jack != king)
  }
}

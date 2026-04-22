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

  test("An Eight can be created") {
    val rank = new Eight
    assert(rank != null)
  }

  test("Eight has correct order, value and category") {
    val rank = new Eight
    assertEquals(rank.order, 8)
    assertEquals(rank.value, 8)
    assertEquals(rank.category, "Even")
  }

  test("Ace has correct order, value and category") {
    val rank = new Ace
    assertEquals(rank.order, 1)
    assertEquals(rank.value, 11)
    assertEquals(rank.category, "Odd")
  }

  test("Two has correct order, value and category") {
    val rank = new Two
    assertEquals(rank.order, 2)
    assertEquals(rank.value, 2)
    assertEquals(rank.category, "Even")
  }

  test("Three has correct order, value and category") {
    val rank = new Three
    assertEquals(rank.order, 3)
    assertEquals(rank.value, 3)
    assertEquals(rank.category, "Odd")
  }

  test("Four has correct order, value and category") {
    val rank = new Four
    assertEquals(rank.order, 4)
    assertEquals(rank.value, 4)
    assertEquals(rank.category, "Even")
  }


  test("Ten has correct order, value and category") {
    val rank = new Ten
    assertEquals(rank.order, 10)
    assertEquals(rank.value, 10)
    assertEquals(rank.category, "Even")
  }

  test("Jack has correct order, value and category") {
    val rank = new Jack
    assertEquals(rank.order, 11)
    assertEquals(rank.value, 10)
    assertEquals(rank.category, "Face Card")
  }

  test("Queen has correct order, value and category") {
    val rank = new Queen
    assertEquals(rank.order, 12)
    assertEquals(rank.value, 10)
    assertEquals(rank.category, "Face Card")
  }

  test("King has correct order, value and category") {
    val rank = new King
    assertEquals(rank.order, 13)
    assertEquals(rank.value, 10)
    assertEquals(rank.category, "Face Card")
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
package cl.uchile.dcc

import munit.FunSuite
import rank._

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

  test("Eight has correct order and value") {
    val rank = new Eight
    assertEquals(rank.order, 8)
    assertEquals(rank.value, 8)
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

  test("Six has correct order and value") {
    val rank = new Six
    assertEquals(rank.order, 6)
    assertEquals(rank.value, 6)
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

  test("isOdd is true for all odd cards") {
    val ace = new Ace
    val three = new Three
    val five = new Five
    val seven = new Seven
    val nine = new Nine

    assert(ace.isOdd)
    assert(three.isOdd)
    assert(five.isOdd)
    assert(seven.isOdd)
    assert(nine.isOdd)
  }

  test("isOdd is false for non odd cards") {
    val two = new Two
    val four = new Four
    val six = new Six
    val eight = new Eight
    val ten = new Ten
    val jack = new Jack
    val queen = new Queen
    val king = new King

    assert(!two.isOdd)
    assert(!four.isOdd)
    assert(!six.isOdd)
    assert(!eight.isOdd)
    assert(!ten.isOdd)
    assert(!jack.isOdd)
    assert(!queen.isOdd)
    assert(!king.isOdd)
  }
}
package cl.uchile.dcc

import munit.FunSuite
import rank._

/**
 * Test suite for the Rank traits and classes.
 *
 * This suite verifies the correct instantiation, order, scoring value,
 * categorization (odd, even), and string representations of all card ranks
 * used in the Malatro game.
 */
class RankTest extends FunSuite {

  /** Verifies that an Ace rank can be instantiated properly. */
  test("An Ace can be created") {
    val rank = new Ace
    assert(rank != null)
  }

  /** Verifies that a Two rank can be instantiated properly. */
  test("A Two can be created") {
    val rank = new Two
    assert(rank != null)
  }

  /** Verifies that a Jack rank can be instantiated properly. */
  test("A Jack can be created") {
    val rank = new Jack
    assert(rank != null)
  }

  /** Verifies that a Queen rank can be instantiated properly. */
  test("A Queen can be created") {
    val rank = new Queen
    assert(rank != null)
  }

  /** Verifies that a King rank can be instantiated properly. */
  test("A King can be created") {
    val rank = new King
    assert(rank != null)
  }

  /** Verifies that an Eight rank can be instantiated properly. */
  test("An Eight can be created") {
    val rank = new Eight
    assert(rank != null)
  }

  /** Verifies the specific order and value of the Eight rank. */
  test("Eight has correct order and value") {
    val rank = new Eight
    assertEquals(rank.order, 8)
    assertEquals(rank.value, 8)
  }

  /** Verifies the specific order and value of the Ace rank. */
  test("Ace has correct order and value") {
    val rank = new Ace
    assertEquals(rank.order, 1)
    assertEquals(rank.value, 11)
  }

  /** Verifies the specific order and value of the Two rank. */
  test("Two has correct order and value") {
    val rank = new Two
    assertEquals(rank.order, 2)
    assertEquals(rank.value, 2)
  }

  /** Verifies the specific order and value of the Three rank. */
  test("Three has correct order and value") {
    val rank = new Three
    assertEquals(rank.order, 3)
    assertEquals(rank.value, 3)
  }

  /** Verifies the specific order and value of the Four rank. */
  test("Four has correct order and value") {
    val rank = new Four
    assertEquals(rank.order, 4)
    assertEquals(rank.value, 4)
  }

  /** Verifies the specific order and value of the Ten rank. */
  test("Ten has correct order and value") {
    val rank = new Ten
    assertEquals(rank.order, 10)
    assertEquals(rank.value, 10)
  }

  /** Verifies the specific order and value of the Jack rank. */
  test("Jack has correct order and value") {
    val rank = new Jack
    assertEquals(rank.order, 11)
    assertEquals(rank.value, 10)
  }

  /** Verifies the specific order and value of the Queen rank. */
  test("Queen has correct order and value") {
    val rank = new Queen
    assertEquals(rank.order, 12)
    assertEquals(rank.value, 10)
  }

  /** Verifies the specific order and value of the King rank. */
  test("King has correct order and value") {
    val rank = new King
    assertEquals(rank.order, 13)
    assertEquals(rank.value, 10)
  }

  /** Verifies the specific order and value of the Six rank. */
  test("Six has correct order and value") {
    val rank = new Six
    assertEquals(rank.order, 6)
    assertEquals(rank.value, 6)
  }

  /** Verifies that distinct rank objects are evaluated as not equal. */
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

  /** Verifies that the isOdd property correctly identifies all odd-valued ranks. */
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

  /** Verifies that the isOdd property is strictly false for even-valued and face cards. */
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

  /**
   * NUEVO TEST: Verifies that getRankName returns the correct string format
   * for every implemented rank in the game.
   */
  test("getRankName returns the correct string for all ranks") {
    assertEquals(new Ace().getRankName, "Ace")
    assertEquals(new Two().getRankName, "Two")
    assertEquals(new Three().getRankName, "Three")
    assertEquals(new Four().getRankName, "Four")
    assertEquals(new Five().getRankName, "Five")
    assertEquals(new Six().getRankName, "Six")
    assertEquals(new Seven().getRankName, "Seven")
    assertEquals(new Eight().getRankName, "Eight")
    assertEquals(new Nine().getRankName, "Nine")
    assertEquals(new Ten().getRankName, "Ten")
    assertEquals(new Jack().getRankName, "Jack")
    assertEquals(new Queen().getRankName, "Queen")
    assertEquals(new King().getRankName, "King")
  }
}
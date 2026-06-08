package cl.uchile.dcc

import pokerhands.*
import rank.*
import suit.*
import jokers.*

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class DoubleDispatchTest extends FunSuite {

  val initialScore = new Score(10, 2)
  val joker = new GreedyJoker

  test("Double dispatch works for Poker Hands (Flush)") {
    val hand = new Flush
    val result = hand.applyScore(initialScore, joker)

    assertEquals(result.total, 20)
    assert(result != null)
  }

  test("Double dispatch works for Suits (Hearts)") {
    val suit = new Hearts
    val result = suit.applyScore(initialScore, joker)

    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Ace)") {
    val rank = new Ace
    val result = rank.applyScore(initialScore, joker)

    assertEquals(result.total, 20)
  }

  test("Double dispatch is consistent across different Jokers") {
    val scary = new ScaryFace
    val even = new EvenSteven
    val rank = new Two

    val res1 = rank.applyScore(initialScore, scary)
    val res2 = rank.applyScore(initialScore, even)

    assertEquals(res1.total, 20)
    assertEquals(res2.total, 20)
  }
}
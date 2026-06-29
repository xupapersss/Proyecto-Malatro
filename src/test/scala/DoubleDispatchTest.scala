package cl.uchile.dcc

import pokerhands._
import rank._
import suit._
import jokers._

import munit.FunSuite


class DoubleDispatchTest extends FunSuite {

  val initialScore = new Score(10, 2)
  val joker = new GreedyJoker

  test("Double dispatch works for Poker Hands (Flush)") {
    val hand = new Flush
    val result = hand.applyScore(initialScore, joker)

    assertEquals(result.total, 20)
    assert(result != null)
  }

  test("Double dispatch works for Poker Hands (HighCard)") {
    val hand = new HighCard
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  test("Double dispatch works for Poker Hands (Pair)") {
    val hand = new Pair
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  test("Double dispatch works for Poker Hands (ThreeOfAKind)") {
    val hand = new ThreeOfaKind
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  test("Double dispatch works for Poker Hands (Flush)") {
    val hand = new Flush
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  test("Double dispatch works for Poker Hands (Straight)") {
    val hand = new Straight
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  test("Double dispatch works for Poker Hands (StraightFlush)") {
    val hand = new StraightFlush
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  test("Double dispatch works for Suits (Hearts)") {
    val suit = new Hearts
    val result = suit.applyScore(initialScore, joker)

    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Suits (Diamonds)") {
    val suit = new Diamonds
    val result = suit.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Suits (Clubs)") {
    val suit = new Clubs
    val result = suit.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Suits (Spades)") {
    val suit = new Spades
    val result = suit.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Ace)") {
    val rank = new Ace
    val result = rank.applyScore(initialScore, joker)

    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Two)") {
    val rank = new Two
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Three)") {
    val rank = new Three
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Four)") {
    val rank = new Four
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Five)") {
    val rank = new Five
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Six)") {
    val rank = new Six
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Seven)") {
    val rank = new Seven
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Eight)") {
    val rank = new Eight
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Nine)") {
    val rank = new Nine
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Ten)") {
    val rank = new Ten
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Jack)") {
    val rank = new Jack
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (Queen)") {
    val rank = new Queen
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  test("Double dispatch works for Ranks (King)") {
    val rank = new King
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

  test("Double dispatch is consistent across different Jokers") {
    val scary = new ScaryFace
    val even = new EvenSteven
    val rank = new Two

    val res1 = rank.applyScore(initialScore, scary)
    val res2 = rank.applyScore(initialScore, even)

    assertEquals(res1.total, 20)
    assertEquals(res2.total, 20)
  }

  import scala.collection.mutable.ArrayBuffer

  test("Card chips returns the rank value") {
    val rank = new Ace
    val suit = new Hearts
    val card = new Card(suit, rank)


    assertEquals(card.chips, rank.value)
  }

  test("Card applyScore processes all jokers in the ArrayBuffer") {
    val rank = new Ace
    val suit = new Hearts
    val card = new Card(suit, rank)
    val joker2 = new EvenSteven

    val jokers = ArrayBuffer(joker,joker2)

    val result = card.applyScore(initialScore,jokers)

    assert(result != null)
  }
}
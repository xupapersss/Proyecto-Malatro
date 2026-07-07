package cl.uchile.dcc

import pokerhands._
import rank._
import suit._
import jokers._

import munit.FunSuite

/**
 * Test suite for verifying the Double Dispatch pattern.
 *
 * This suite ensures that the `applyScore` methods correctly delegate
 * scoring modifications to the Joker's specific methods based on the
 * exact type of PokerHand, Suit, or Rank being evaluated.
 */
class DoubleDispatchTest extends FunSuite {

  val initialScore = new Score(10, 2)
  val joker = new GreedyJoker

  /** Verifies that a Flush hand triggers the correct double dispatch method. */
  test("Double dispatch works for Poker Hands (Flush)") {
    val hand = new Flush
    val result = hand.applyScore(initialScore, joker)

    assertEquals(result.total, 20)
    assert(result != null)
  }

  /** Verifies that a High Card hand triggers the correct double dispatch method. */
  test("Double dispatch works for Poker Hands (HighCard)") {
    val hand = new HighCard
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  /** Verifies that a Pair hand triggers the correct double dispatch method. */
  test("Double dispatch works for Poker Hands (Pair)") {
    val hand = new Pair
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  /** Verifies that a Three of a Kind hand triggers the correct double dispatch method. */
  test("Double dispatch works for Poker Hands (ThreeOfAKind)") {
    val hand = new ThreeOfaKind
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  /** Verifies that a Straight hand triggers the correct double dispatch method. */
  test("Double dispatch works for Poker Hands (Straight)") {
    val hand = new Straight
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  /** Verifies that a Straight Flush hand triggers the correct double dispatch method. */
  test("Double dispatch works for Poker Hands (StraightFlush)") {
    val hand = new StraightFlush
    val result = hand.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
    assert(result != null)
  }

  /** Verifies that the Hearts suit triggers the correct double dispatch method. */
  test("Double dispatch works for Suits (Hearts)") {
    val suit = new Hearts
    val result = suit.applyScore(initialScore, joker)

    assertEquals(result.total, 20)
  }

  /** Verifies that the Diamonds suit triggers the correct double dispatch method. */
  test("Double dispatch works for Suits (Diamonds)") {
    val suit = new Diamonds
    val result = suit.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Clubs suit triggers the correct double dispatch method. */
  test("Double dispatch works for Suits (Clubs)") {
    val suit = new Clubs
    val result = suit.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Spades suit triggers the correct double dispatch method. */
  test("Double dispatch works for Suits (Spades)") {
    val suit = new Spades
    val result = suit.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Ace rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Ace)") {
    val rank = new Ace
    val result = rank.applyScore(initialScore, joker)

    assertEquals(result.total, 20)
  }

  /** Verifies that the Two rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Two)") {
    val rank = new Two
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Three rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Three)") {
    val rank = new Three
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Four rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Four)") {
    val rank = new Four
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Five rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Five)") {
    val rank = new Five
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Six rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Six)") {
    val rank = new Six
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Seven rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Seven)") {
    val rank = new Seven
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Eight rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Eight)") {
    val rank = new Eight
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Nine rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Nine)") {
    val rank = new Nine
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Ten rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Ten)") {
    val rank = new Ten
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Jack rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Jack)") {
    val rank = new Jack
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the Queen rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (Queen)") {
    val rank = new Queen
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that the King rank triggers the correct double dispatch method. */
  test("Double dispatch works for Ranks (King)") {
    val rank = new King
    val result = rank.applyScore(initialScore, joker)
    assertEquals(result.total, 20)
  }

  /** Verifies that multiple different Jokers can evaluate the same rank consistently. */
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

  /** Verifies that a Card object correctly delegates its chip value retrieval to its rank. */
  test("Card chips returns the rank value") {
    val rank = new Ace
    val suit = new Hearts
    val card = new Card(suit, rank)

    assertEquals(card.chips, rank.value)
  }

  /** Verifies that a Card properly loops through and applies an ArrayBuffer of Jokers. */
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
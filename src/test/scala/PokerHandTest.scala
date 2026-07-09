package cl.uchile.dcc

import pokerhands.*
import rank.*
import suit.*

import munit.FunSuite

import scala.collection.mutable.{ArrayBuffer, ArrayStack}

/**
 * Test suite for verifying the logic of poker hand detection and prioritization.
 *
 * This suite ensures that the system correctly identifies specific poker
 * hands (Pair, Straight, Flush, etc.) and that the `HandPriority` class
 * correctly resolves the strongest hand from a set of cards.
 */
class PokerHandTest extends FunSuite {

  /** Verifies that the fallback High Card hand validates any combination of cards. */
  test("isHCard is detected correctly") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Two),
      new Card(new Spades, new Queen),
      new Card(new Clubs, new Five),
      new Card(new Hearts, new Three),
      new Card(new Clubs, new Jack),
    )
    val result = new HighCard().isHCard(cards)

    assert(result)
  }

  /** Verifies that a Pair hand is accurately identified from a collection of cards. */
  test("Pair is detected correctly") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Two),
      new Card(new Spades, new Three),
      new Card(new Clubs, new Five),
      new Card(new Hearts, new Three),
      new Card(new Clubs, new Jack),
    )

    val result = new Pair().isPair(cards)

    assert(result)
  }

  /** Verifies that a Three of a Kind hand is accurately identified. */
  test("Three of a kind is detected correctly") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Jack),
      new Card(new Spades, new Three),
      new Card(new Clubs, new Five),
      new Card(new Hearts, new Jack),
      new Card(new Clubs, new Jack),
    )

    val result = new ThreeOfaKind().isTOaK(cards)

    assert(result)
  }

  /** Verifies that a Flush hand (5 cards of the same suit) is accurately identified. */
  test("Flush is detected correctly") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Jack),
      new Card(new Hearts, new Three),
      new Card(new Hearts, new Five),
      new Card(new Hearts, new Ace),
      new Card(new Hearts, new Six)
    )

    val result = new Flush().isFlush(cards)

    assert(result)
  }

  /** Verifies that a standard consecutive sequence triggers a Straight. */
  test("Straight is detected for consecutive cards") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Two),
      new Card(new Spades, new Three),
      new Card(new Clubs, new Four),
      new Card(new Diamonds, new Five),
      new Card(new Hearts, new Six)
    )

    val result = new Straight().isStraight(cards)

    assert(result)
  }

  /** Verifies that the lowest possible Straight (A-2-3-4-5) is identified properly. */
  test("Straight is detected for A-2-3-4-5") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Ace),
      new Card(new Spades, new Two),
      new Card(new Clubs, new Three),
      new Card(new Diamonds, new Four),
      new Card(new Hearts, new Five)
    )

    val result = new Straight().isStraight(cards)

    assert(result)
  }

  /** Verifies that the highest possible Straight (10-J-Q-K-A) is identified properly. */
  test("Straight is detected for 10-J-Q-K-A") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Ten),
      new Card(new Spades, new Jack),
      new Card(new Clubs, new Queen),
      new Card(new Diamonds, new King),
      new Card(new Hearts, new Ace)
    )

    val result = new Straight().isStraight(cards)

    assert(result)
  }

  /** Verifies that a sequence of identical suits triggers a Straight Flush. */
  test("Straight Flush is detected") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Two),
      new Card(new Hearts, new Three),
      new Card(new Hearts, new Four),
      new Card(new Hearts, new Five),
      new Card(new Hearts, new Six)
    )

    val result = new StraightFlush().isStraightFlush(cards)

    assert(result)
  }

  /** Verifies that HandPriority resolves a Straight Flush over lower combinations. */
  test("HandPriority chooses StraightFlush with highest priority") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Ten),
      new Card(new Hearts, new Jack),
      new Card(new Hearts, new Queen),
      new Card(new Hearts, new King),
      new Card(new Hearts, new Ace)
    )

    val hand = new Hand(cards,
      ArrayBuffer())

    val result = new HandPriority(hand).bestHand

    assertEquals(result.basechips, 100)
    assertEquals(result.basemult, 8)
  }

  /** Verifies that HandPriority accurately resolves a Flush. */
  test("HandPriority chooses Flush correctly") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Two),
      new Card(new Hearts, new Five),
      new Card(new Hearts, new Seven),
      new Card(new Hearts, new Nine),
      new Card(new Hearts, new King)
    )

    val hand = new Hand(cards,
      ArrayBuffer())

    val result = new HandPriority(hand).bestHand

    assertEquals(result.basechips, 35)
    assertEquals(result.basemult, 4)
  }

  /** Verifies that HandPriority accurately resolves a Straight. */
  test("HandPriority chooses Straight correctly") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Two),
      new Card(new Spades, new Three),
      new Card(new Clubs, new Four),
      new Card(new Diamonds, new Five),
      new Card(new Hearts, new Six)
    )

    val hand = new Hand(cards,
      ArrayBuffer())

    val result = new HandPriority(hand).bestHand

    assertEquals(result.basechips, 30)
    assertEquals(result.basemult, 4)
  }

  /** Verifies that HandPriority accurately resolves a Three of a Kind. */
  test("HandPriority chooses ThreeOfAKind correctly") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Queen),
      new Card(new Spades, new Queen),
      new Card(new Clubs, new Queen),
      new Card(new Diamonds, new Five),
      new Card(new Hearts, new Two)
    )

    val hand = new Hand(cards,
      ArrayBuffer())

    val result = new HandPriority(hand).bestHand

    assertEquals(result.basechips, 30)
    assertEquals(result.basemult, 3)
  }

  /** Verifies that HandPriority accurately resolves a Pair. */
  test("HandPriority chooses Pair correctly") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Two),
      new Card(new Spades, new Two),
      new Card(new Clubs, new Five),
      new Card(new Diamonds, new Seven),
      new Card(new Hearts, new Nine)
    )

    val hand = new Hand(cards,
      ArrayBuffer())

    val result = new HandPriority(hand).bestHand

    assertEquals(result.basechips, 10)
    assertEquals(result.basemult, 2)
  }

  /** Verifies that HandPriority safely falls back to High Card when no hand matches. */
  test("HandPriority falls back to HighCard") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Two),
      new Card(new Spades, new Five),
      new Card(new Clubs, new Seven),
      new Card(new Diamonds, new Nine),
      new Card(new Hearts, new King)
    )

    val hand = new Hand(cards,
      ArrayBuffer())

    val result = new HandPriority(hand).bestHand

    assertEquals(result.basechips, 5)
    assertEquals(result.basemult, 1)
  }

  /** Verifies that scoringCards isolates only the specific cards that form the winning hand. */
  test("scoringCard returns only the cards present in the poker hand"){

    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Two)
    val c3 = new Card(new Clubs, new Seven)
    val c4 = new Card(new Diamonds, new Two)
    val c5 = new Card(new Hearts, new King)

    val cards = ArrayBuffer(c1,c2,c3,c4,c5)
    val hand = new Hand(cards, ArrayBuffer())
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = playedPokerHand.scoringCards(cards)

    // We expect the Three of a Kind cards (the three 'Twos')
    val expectedResult = ArrayBuffer(c1,c2,c4)

    assertEquals(result,expectedResult)
  }

  // ==========================================
  // Tests for pokerHandName property
  // ==========================================

  /** Verifies that pokerHandName returns the correct string for HighCard. */
  test("pokerHandName should return 'High Card' for HighCard hand") {
    val hand = new HighCard()
    assertEquals(hand.pokerHandName, "High Card")
  }

  /** Verifies that pokerHandName returns the correct string for Pair. */
  test("pokerHandName should return 'Pair' for Pair hand") {
    val hand = new Pair()
    assertEquals(hand.pokerHandName, "Pair")
  }

  /** Verifies that pokerHandName returns the correct string for ThreeOfaKind. */
  test("pokerHandName should return 'Three Of a Kind' for ThreeOfaKind hand") {
    val hand = new ThreeOfaKind()
    assertEquals(hand.pokerHandName, "Three Of a Kind")
  }

  /** Verifies that pokerHandName returns the correct string for Flush. */
  test("pokerHandName should return 'Flush' for Flush hand") {
    val hand = new Flush()
    assertEquals(hand.pokerHandName, "Flush")
  }

  /** Verifies that pokerHandName returns the correct string for Straight. */
  test("pokerHandName should return 'Straight' for Straight hand") {
    val hand = new Straight()
    assertEquals(hand.pokerHandName, "Straight")
  }

  /** Verifies that pokerHandName returns the correct string for StraightFlush. */
  test("pokerHandName should return 'Straight Flush' for StraightFlush hand") {
    val hand = new StraightFlush()
    assertEquals(hand.pokerHandName, "Straight Flush")
  }
}
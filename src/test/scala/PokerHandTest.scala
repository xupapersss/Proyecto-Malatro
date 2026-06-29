package cl.uchile.dcc

import pokerhands.*
import rank.*
import suit.*

import munit.FunSuite

import scala.collection.mutable.{ArrayBuffer, ArrayStack}

class PokerHandTest extends FunSuite {

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
    val expectedResult = ArrayBuffer(c1,c2,c4)

    assertEquals(result,expectedResult)
  }
}

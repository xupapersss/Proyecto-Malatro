package cl.uchile.dcc

import cl.uchile.dcc.PokerHands._
import cl.uchile.dcc.Rank._
import cl.uchile.dcc.Suit._
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

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
}

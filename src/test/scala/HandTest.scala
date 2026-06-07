package cl.uchile.dcc
import munit.FunSuite
import scala.collection.mutable.ArrayBuffer
import Suit._
import Rank._
import Jokers._

class HandTest extends FunSuite {

  test("addCards adds new cards to the hand correctly") {
    val card1 = new Card(new Hearts, new Two)
    val card2 = new Card(new Spades, new Three)

    val newCard1 = new Card(new Clubs, new Four)
    val newCard2 = new Card(new Diamonds, new Five)

    val hand = new Hand(
      ArrayBuffer(card1, card2),
      ArrayBuffer()
    )

    hand.addCards(ArrayBuffer(newCard1, newCard2))

    val result = hand.cards

    assertEquals(result.length, 4)
    assert(result.contains(card1))
    assert(result.contains(card2))
    assert(result.contains(newCard1))
    assert(result.contains(newCard2))
  }

  test("addJokers adds jokers to the hand correctly") {
    val joker1 = new DeviousJoker
    val joker2 = new GreedyJoker


    val hand = new Hand(
      ArrayBuffer(),
      ArrayBuffer(joker1)
    )

    hand.addJokers(ArrayBuffer(joker2))

    val result = hand.handJokers

    assertEquals(result.length, 2)
    assert(result.contains(joker1))
    assert(result.contains(joker2))
  }

  test("discardCards removes cards by indexes correctly") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4),
      ArrayBuffer()
    )

    // eliminate positions 1 y 2 (c2 y c3)
    hand.discardCards(ArrayBuffer(1, 2))

    val result = hand.cards

    assertEquals(result.length, 2)
    assert(result.contains(c1))
    assert(result.contains(c4))
  }

  test("discardJokers removes second joker correctly") {
    val j1 = new DeviousJoker
    val j2 = new DeviousJoker

    val hand = new Hand(
      ArrayBuffer(),
      ArrayBuffer(j1, j2)
    )

    hand.discardJokers(ArrayBuffer(1))

    val result = hand.jokers

    assertEquals(result, List(j1))
  }

  test("playHand returns selected cards and removes them from Hand") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val c6 = new Card(new Spades, new Seven)
    val c7 = new Card(new Clubs, new Eight)
    val c8 = new Card(new Diamonds, new Nine)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5, c6, c7, c8),
      ArrayBuffer()
    )

    // play cards in positions 2, 4 y 6
    val playedCards = hand.playHand(ArrayBuffer(2, 4, 6))

    // verify played cards
    assertEquals(playedCards, ArrayBuffer(c3, c5, c7))

    // verify that played cards were deleted from hand
    assertEquals(hand.cards, List(c1, c2, c4, c6, c8))
  }
  test("Hand cannot start 8 or more cards") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val c6 = new Card(new Spades, new Seven)
    val c7 = new Card(new Clubs, new Eight)
    val c8 = new Card(new Diamonds, new Nine)
    val c9 = new Card(new Hearts, new Ace)

    intercept[InvalidHand] {
      new Hand(
        ArrayBuffer(c1, c2, c3, c4, c5, c6, c7, c8, c9),
        ArrayBuffer())
    }
  }

  test("Hand cannot have more than 8 cards when adding new ones") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val c6 = new Card(new Spades, new Seven)
    val c7 = new Card(new Clubs, new Eight)
    val c8 = new Card(new Diamonds, new Nine)
    val c9 = new Card(new Hearts, new Ace)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5, c6),
      ArrayBuffer()
    )

    intercept[InvalidHand] {
      hand.addCards(ArrayBuffer(c7, c8, c9))
    }
  }

  test("Hand cannot start with more than 2 Jokers") {
    val j1 = new DeviousJoker
    val j2 = new EvenSteven
    val j3 = new GreedyJoker


    intercept[InvalidHand] {
      val hand = new Hand(
        ArrayBuffer(),
        ArrayBuffer(j1, j2, j3)
      )
    }
  }

  test("Hand cannot have more than 2 jokers when adding new ones") {
    val j1 = new DeviousJoker
    val j2 = new EvenSteven
    val j3 = new GreedyJoker

    val hand = new Hand(
      ArrayBuffer(),
      ArrayBuffer(j1)
    )

    intercept[InvalidHand] {
      hand.addJokers(ArrayBuffer(j2, j3))
    }
  }

  test("discardCards cannot have an invalid index") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val j1 = new DeviousJoker
    val j2 = new GreedyJoker


    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4),
      ArrayBuffer(j1, j2))

    intercept[InvalidHand] {
      hand.discardCards(ArrayBuffer(2, 3, 6))
    }
  }

  test("discardJokers cannot have an invalid index") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val j1 = new DeviousJoker
    val j2 = new GreedyJoker


    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4),
      ArrayBuffer(j1, j2))

    intercept[InvalidHand] {
      hand.discardJokers(ArrayBuffer(6))
    }
  }

  test("playHand cannot have more than 5 cards") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val c6 = new Card(new Spades, new Seven)
    val c7 = new Card(new Clubs, new Eight)
    val c8 = new Card(new Diamonds, new Nine)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5, c6, c7, c8),
      ArrayBuffer()
    )

    intercept[InvalidHand]{
      hand.playHand(ArrayBuffer(2,3,4,1,6,7))
    }
  }

  test("playHand cannot have less than 1 card") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val c6 = new Card(new Spades, new Seven)
    val c7 = new Card(new Clubs, new Eight)
    val c8 = new Card(new Diamonds, new Nine)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5, c6, c7, c8),
      ArrayBuffer()
    )

    intercept[InvalidHand] {
      hand.playHand(ArrayBuffer())
    }
  }

  test("playHand cannot have an index that doesn't exist in the hand") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val c6 = new Card(new Spades, new Seven)
    val c7 = new Card(new Clubs, new Eight)
    val c8 = new Card(new Diamonds, new Nine)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5, c6, c7, c8),
      ArrayBuffer()
    )

    intercept[InvalidHand] {
      hand.playHand(ArrayBuffer(1,2,3,4,10))
    }
  }

  test("you cannot discard more than 5 cards"){
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val c6 = new Card(new Spades, new Seven)
    val c7 = new Card(new Clubs, new Eight)
    val c8 = new Card(new Diamonds, new Nine)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5, c6, c7, c8),
      ArrayBuffer()
    )

    intercept[InvalidHand] {
      hand.discardCards(ArrayBuffer(1,2,3,4,5,6))
    }
  }

  test("you cannot discard less than 1 card"){
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val c6 = new Card(new Spades, new Seven)
    val c7 = new Card(new Clubs, new Eight)
    val c8 = new Card(new Diamonds, new Nine)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5, c6, c7, c8),
      ArrayBuffer()
    )

    intercept[InvalidHand] {
      hand.discardCards(ArrayBuffer())
    }
  }

  test("discardCards cannot have an index that doesn't exist in the hand") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val j1 = new DeviousJoker
    val j2 = new GreedyJoker


    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4),
      ArrayBuffer(j1, j2))

    intercept[InvalidHand] {
      hand.discardCards(ArrayBuffer(6))
    }
  }

  test("you cannot play a set of cards more than 3 times"){
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5),
      ArrayBuffer()
    )

    hand.playHand(ArrayBuffer(0))

    hand.playHand(ArrayBuffer(0))

    hand.playHand(ArrayBuffer(0))

    intercept[InvalidHand] {
      hand.playHand(ArrayBuffer(0))
    }
  }

  test("you cannot discard more than 3 times"){
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5),
      ArrayBuffer()
    )

    hand.discardCards(ArrayBuffer(0))

    hand.discardCards(ArrayBuffer(0))

    hand.discardCards(ArrayBuffer(0))

    intercept[InvalidHand] {
      hand.discardCards(ArrayBuffer(0))
    }
  }
}
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
    val joker2 = new DeviousJoker

    val newJoker1 = new DeviousJoker
    val newJoker2 = new DeviousJoker

    val hand = new Hand(
      ArrayBuffer(),
      ArrayBuffer(joker1, joker2)
    )

    hand.addJokers(ArrayBuffer(newJoker1, newJoker2))

    val result = hand.handJokers

    assertEquals(result.length, 4)
    assert(result.contains(joker1))
    assert(result.contains(joker2))
    assert(result.contains(newJoker1))
    assert(result.contains(newJoker2))
  }
}
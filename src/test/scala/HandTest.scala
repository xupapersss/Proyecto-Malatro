package cl.uchile.dcc
import munit.FunSuite
import scala.collection.mutable.ArrayBuffer
import Suit._
import Rank._
import Jokers._

class HandTest extends FunSuite {

  test("A Hand can be created") {
    val cards = ArrayBuffer[Card]()
    val jokers = ArrayBuffer[Joker]()

    val hand = new Hand(cards, jokers)

    assert(hand != null)
  }

  test("Hand stores cards correctly") {
    val card1 = new Card(new Clubs, new Ace)
    val card2 = new Card(new Hearts, new King)

    val cards = ArrayBuffer(card1, card2)
    val jokers = ArrayBuffer[Joker]()

    val hand = new Hand(cards, jokers)

    assertEquals(hand.cards, cards)
  }

  test("Hand stores jokers correctly") {
    val cards = ArrayBuffer[Card]()
    val joker1 = new GreedyJoker
    val joker2 = new EvenSteven

    val jokers = ArrayBuffer(joker1, joker2)

    val hand = new Hand(cards, jokers)

    assertEquals(hand.jokers, jokers)
  }

  test("Hand can contain both cards and jokers") {
    val card = new Card(new Spades, new Queen)
    val joker = new ScaryFace

    val hand = new Hand(
      ArrayBuffer(card),
      ArrayBuffer(joker)
    )

    assertEquals(hand.cards.size, 1)
    assertEquals(hand.jokers.size, 1)
  }

  test("Cards in hand can be modified") {
    val hand = new Hand(ArrayBuffer(), ArrayBuffer())

    val card = new Card(new Diamonds, new Two)
    hand.cards += card

    assertEquals(hand.cards.size, 1)
    assertEquals(hand.cards.head, card)
  }

  test("Jokers in hand can be modified") {
    val hand = new Hand(ArrayBuffer(), ArrayBuffer())

    val joker = new DeviousJoker
    hand.jokers += joker

    assertEquals(hand.jokers.size, 1)
    assertEquals(hand.jokers.head, joker)
  }

  test("addCards preserves existing cards and adds new ones") {
    val card1 = new Card(new Hearts, new Two)
    val card2 = new Card(new Spades, new Three)

    val newCard1 = new Card(new Clubs, new Four)
    val newCard2 = new Card(new Diamonds, new Five)

    val hand = new Hand(ArrayBuffer(card1, card2), ArrayBuffer())

    hand.addCards(ArrayBuffer(newCard1, newCard2))

    assert(hand.cards.contains(card1))
    assert(hand.cards.contains(card2))
    assert(hand.cards.contains(newCard1))
    assert(hand.cards.contains(newCard2))
  }
}
package cl.uchile.dcc
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
import suit.*
import rank.*
import jokers.*

import cl.uchile.dcc.pokerhands.{HandPriority, ThreeOfaKind}

/**
 * Test suite for the Hand class.
 *
 * This suite comprehensively verifies the mechanics of the player's hand,
 * including adding/discarding cards and jokers, validating game rules (max limits),
 * playing subsets of cards, and calculating final scores with Joker effects.
 */
class HandTest extends FunSuite {

  /** Verifies that addCards successfully appends new cards to the hand without exceeding limits. */
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

  /** Verifies that addJokers successfully appends new jokers to the hand without exceeding limits. */
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

  /** Verifies that discardCards correctly removes the specified cards based on their descending indices. */
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

  /** Verifies that discardJokers correctly removes a specific joker without shifting issues. */
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

  /** Verifies that playing a hand extracts the correct cards and removes them from the internal buffer. */
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

  /** Verifies that creating a Hand with more than 8 cards throws an InvalidHand exception. */
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

  /** Verifies that adding cards beyond the 8-card limit throws an InvalidHand exception. */
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

  /** Verifies that creating a Hand with more than 2 jokers throws an InvalidHand exception. */
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

  /** Verifies that adding jokers beyond the 2-joker limit throws an InvalidHand exception. */
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

  /** Verifies that discarding an out-of-bounds card index throws an InvalidHand exception. */
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

  /** Verifies that discarding an out-of-bounds joker index throws an InvalidHand exception. */
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

  /** Verifies that attempting to play more than 5 cards at once throws an InvalidHand exception. */
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

  /** Verifies that attempting to play 0 cards throws an InvalidHand exception. */
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

  /** Verifies that attempting to play an out-of-bounds card index throws an InvalidHand exception. */
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

  /** Verifies that attempting to discard more than 5 cards at once throws an InvalidHand exception. */
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

  /** Verifies that attempting to discard 0 cards throws an InvalidHand exception. */
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

  /** Verifies that attempting to discard a card index that doesn't exist throws an InvalidHand exception. */
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

  /** Verifies that attempting to play more than 3 times total throws an InvalidHand exception. */
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

  /** Verifies that attempting to discard more than 3 times total throws an InvalidHand exception. */
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

  /** Verifies the correct score calculation for a Straight enhanced by a Devious Joker. */
  test("handScore works as intended for a straight") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val j1 = new DeviousJoker

    val hand = new Hand(ArrayBuffer(c1,c2,c3,c4,c5),
      ArrayBuffer(j1))

    val playedPokerHand = HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)
    val expectedScore = new Score(playedPokerHand.basechips + 20 + 100, playedPokerHand.basemult)

    assertEquals(result,expectedScore.total)
  }

  /** Verifies the correct score calculation for a Pair enhanced by an Even Steven Joker. */
  test("handScore works for a Pair and EvenSteven") {
    val c1 = new Card(new Hearts, new Eight)
    val c2 = new Card(new Spades, new Eight)
    val c3 = new Card(new Clubs, new Three)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Seven)

    val j1 = new EvenSteven

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 16,
      playedPokerHand.basemult + 8
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies the correct score calculation for a Flush enhanced by a Greedy Joker. */
  test("handScore works for a Flush and GreedyJoker") {
    val c1 = new Card(new Diamonds, new Two)
    val c2 = new Card(new Diamonds, new Four)
    val c3 = new Card(new Diamonds, new Six)
    val c4 = new Card(new Diamonds, new Eight)
    val c5 = new Card(new Diamonds, new Ten)

    val j1 = new GreedyJoker

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 30,
      playedPokerHand.basemult + 15
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies the correct score calculation for a High Card enhanced by a Scary Face Joker. */
  test("handScore works for a HighCard and ScaryFace") {
    val c1 = new Card(new Hearts, new King)
    val c2 = new Card(new Spades, new Nine)
    val c3 = new Card(new Clubs, new Seven)
    val c4 = new Card(new Diamonds, new Four)
    val c5 = new Card(new Hearts, new Two)

    val j1 = new ScaryFace

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 10 + 30,
      playedPokerHand.basemult
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies the correct score calculation for a Three of a Kind enhanced by an Even Steven Joker. */
  test("handScore works for a Three of a Kind and EvenSteven") {
    val c1 = new Card(new Diamonds, new Four)
    val c2 = new Card(new Spades, new Four)
    val c3 = new Card(new Hearts, new Four)
    val c4 = new Card(new Clubs, new Five)
    val c5 = new Card(new Diamonds, new Nine)

    val j1 = new EvenSteven

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 12,
      playedPokerHand.basemult + 12
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies the correct score calculation for a Straight Flush enhanced by a Devious Joker. */
  test("handScore works for a StraightFlush and DeviousJoker") {
    val c1 = new Card(new Clubs, new Five)
    val c2 = new Card(new Clubs, new Six)
    val c3 = new Card(new Clubs, new Seven)
    val c4 = new Card(new Clubs, new Eight)
    val c5 = new Card(new Clubs, new Nine)

    val j1 = new DeviousJoker

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 35 + 100,
      playedPokerHand.basemult
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies that the score is un-altered if the Even Steven condition is not met. */
  test("handScore works as intended when EvenSteven doesn't add points") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Clubs, new Ace)
    val c3 = new Card(new Diamonds, new Ace)
    val c4 = new Card(new Hearts, new Four)
    val c5 = new Card(new Diamonds, new Six)

    val j1 = new EvenSteven

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 22,
      playedPokerHand.basemult
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies that the score is un-altered if the Greedy Joker condition is not met. */
  test("handScore works as intended when GreedyJoker doesn't add points") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Diamonds, new Four)
    val c3 = new Card(new Clubs, new Two)
    val c4 = new Card(new Spades, new Two)
    val c5 = new Card(new Diamonds, new Ten)

    val j1 = new GreedyJoker

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 6,
      playedPokerHand.basemult
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies that the score is un-altered if the Scary Face condition is not met. */
  test("handScore works as intended when ScaryFace doesn't add points") {
    val c1 = new Card(new Hearts, new Ace)
    val c2 = new Card(new Diamonds, new King)
    val c3 = new Card(new Clubs, new Queen)
    val c4 = new Card(new Spades, new Jack)
    val c5 = new Card(new Diamonds, new Eight)

    val j1 = new ScaryFace

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 11,
      playedPokerHand.basemult
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies that multiple jokers (Even Steven + Greedy Joker) correctly stack their effects. */
  test("handScore works EvenSteven and GreedyJoker") {
    val c1 = new Card(new Diamonds, new Four)
    val c2 = new Card(new Spades, new Four)
    val c3 = new Card(new Hearts, new Four)
    val c4 = new Card(new Clubs, new Five)
    val c5 = new Card(new Diamonds, new Nine)

    val j1 = new EvenSteven
    val j2 = new GreedyJoker

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1, j2))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 12,
      playedPokerHand.basemult + 15
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies that multiple jokers (Devious Joker + Even Steven) correctly stack their effects. */
  test("handScore works for DeviousJoker and EvenSteven") {
    val c1 = new Card(new Clubs, new Five)
    val c2 = new Card(new Clubs, new Six)
    val c3 = new Card(new Clubs, new Seven)
    val c4 = new Card(new Clubs, new Eight)
    val c5 = new Card(new Clubs, new Nine)

    val j1 = new DeviousJoker
    val j2 = new EvenSteven

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1,j2))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 35 + 100,
      playedPokerHand.basemult + 8
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies that multiple jokers (Scary Face + Greedy Joker) correctly stack their effects. */
  test("handScore works for ScaryFace and GreedyJoker") {
    val c1 = new Card(new Diamonds, new Ace)
    val c2 = new Card(new Diamonds, new King)
    val c3 = new Card(new Clubs, new Queen)
    val c4 = new Card(new Diamonds, new Jack)
    val c5 = new Card(new Hearts, new Ten)

    val j1 = new ScaryFace
    val j2 = new GreedyJoker

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5), ArrayBuffer(j1, j2))
    val playedPokerHand = new HandPriority(hand).bestHand

    val result = hand.handScore(hand.handCards, playedPokerHand)

    val expectedScore = new Score(
      playedPokerHand.basechips + 51 + 90,
      playedPokerHand.basemult + 9
    )

    assertEquals(result, expectedScore.total)
  }

  /** Verifies that getHandCards correctly returns the internal mutable ArrayBuffer of cards. */
  test("getHandCards should return the exact ArrayBuffer of cards it was initialized with") {
    // 1. Setup: Creamos un arreglo de cartas conocido
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val expectedCards = ArrayBuffer(c1, c2)

    // 2. Instanciamos la mano (dejamos los jokers vacíos para aislar la prueba)
    val hand = new Hand(expectedCards, ArrayBuffer[Joker]())

    // 3. Ejecución
    val result = hand.getHandCards

    // 4. Verificación: Comparamos que sea la misma colección y tenga el mismo tamaño
    assertEquals(result, expectedCards)
    assertEquals(result.length, 2)
  }

  /** Verifies that getHandJokers correctly returns the internal mutable ArrayBuffer of jokers. */
  test("getHandJokers should return the exact ArrayBuffer of jokers it was initialized with") {
    // 1. Setup: Creamos un arreglo de jokers conocido
    val j1 = new GreedyJoker()
    val j2 = new EvenSteven()
    val expectedJokers = ArrayBuffer[Joker](j1, j2)

    // 2. Instanciamos la mano (dejamos las cartas vacías para aislar la prueba)
    val hand = new Hand(ArrayBuffer[Card](), expectedJokers)

    // 3. Ejecución
    val result = hand.getHandJokers

    // 4. Verificación: Comparamos que sea la misma colección y tenga el mismo tamaño
    assertEquals(result, expectedJokers)
    assertEquals(result.length, 2)
  }

  /** Verifies that getDiscardCount accurately tracks the number of discard actions performed. */
  test("getDiscardCount should accurately track the number of discard actions") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4), ArrayBuffer())

    // Initial state should be 0
    assertEquals(hand.getDiscardCount, 0)

    // After first discard
    hand.discardCards(ArrayBuffer(0))
    assertEquals(hand.getDiscardCount, 1)

    // After second discard
    hand.discardCards(ArrayBuffer(0))
    assertEquals(hand.getDiscardCount, 2)
  }

  /** Verifies that playing multiple 10-value cards alongside a Three of a Kind doesn't crash the evaluator. */
  test("ThreeOfaKind does not crash and calculates correctly when other 10-value cards are played") {
    // 1. Configuramos exactamente la mano que causó el problema
    val c1 = new Card(new Spades, new Queen) // index 0
    val c2 = new Card(new Diamonds, new Queen) // index 1
    val c3 = new Card(new Spades, new Ace) // index 2
    val c4 = new Card(new Diamonds, new King) // index 3
    val c5 = new Card(new Hearts, new Eight) // index 4
    val c6 = new Card(new Clubs, new Queen) // index 5
    val c7 = new Card(new Diamonds, new Nine) // index 6
    val c8 = new Card(new Diamonds, new Five) // index 7

    val j1 = new EvenSteven
    val j2 = new GreedyJoker

    val hand = new Hand(
      ArrayBuffer(c1, c2, c3, c4, c5, c6, c7, c8),
      ArrayBuffer(j1, j2)
    )

    // 2. Extraemos las cartas usando los mismos índices: 0, 1, 5, 2, 3
    val playedCards = hand.playHand(ArrayBuffer(0, 1, 5, 2, 3))

    // 3. Simulamos la creación de la mano temporal que implementamos en PlayersTurn
    val tempHand = new Hand(playedCards, ArrayBuffer())
    val bestHand = new HandPriority(tempHand).bestHand

    // 4. Verificamos que HandPriority detectó correctamente el Trío (Three of a Kind)
    assert(bestHand.isInstanceOf[ThreeOfaKind], "The hand was not recognized as Three of a Kind")

    // 5. Ejecutamos la función de puntaje (aquí era donde el programa lanzaba NoSuchElementException)
    val finalScore = hand.handScore(playedCards, bestHand)

    // 6. Validamos que la matemática es exacta:
    // Chips: Base (30) + 3 Queens (10+10+10 = 30) = 60
    // Mult: Base (3) + Greedy Joker por UNA sola carta de diamante jugada y válida (Q de diamantes) (+3) = 6
    // Even Steven NO suma nada porque las Q, K y A no son pares.
    // Total: 60 * 6 = 360
    assertEquals(finalScore, 360)
  }
}

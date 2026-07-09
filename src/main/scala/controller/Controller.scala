package cl.uchile.dcc
package controller

import controller.states.{GameState, InitialSetup, Lose}
import controller.observer.{Observer, Subject}
import rank.*
import suit.*
import jokers.*
import pokerhands.*
import scala.util.Random
import cl.uchile.dcc.InvalidHand

import scala.collection.mutable.ArrayBuffer

/**
 * The main controller of the Malatro game.
 *
 * Manages the game's state machine, the player's hand, the deck, and the scoring system.
 * It acts as an Observer to listen for events triggered by the Hand (such as running out of plays or discards).
 */
class Controller extends Observer {

  var hand: Hand = _
  var currentScore: Int = 0
  var targetScore: Int = 0
  private var state: GameState = new InitialSetup
  private var deck: ArrayBuffer[Card] = ArrayBuffer()

  /**
   * Receives and processes notifications from observed subjects.
   *
   * @param subject  the subject sending the notification
   * @param response the notification message containing the event details
   */
  override def update(subject: Subject, response: Any): Unit = {
    response match {
      case "Out of plays" =>
        println("You are out of plays")
        setState(new Lose)

      case "Out of discards" =>
        println("You are out of discards! You must play your cards.")

      case _ =>
    }
  }

  /**
   * Transitions the game to a new state.
   *
   * @param state the new GameState to transition to
   */
  def setState(state: GameState): Unit =
    this.state = state

  /**
   * Safely executes an operation, catching and handling expected game exceptions.
   *
   * @param op the operation to execute
   */
  private def safeRun(op: () => Unit): Unit =
    try op()
    catch {
      case e: InvalidTransitionException =>
        println(e.getMessage)
      case e: InvalidHand =>
        println(s" ${e.getMessage}")
    }

  /** Initiates the game state machine. */
  def start(): Unit =
    safeRun(() => state.start(this))

  /**
   * Attempts to play the selected cards from the hand.
   *
   * @param indexes the indices of the cards to play
   */
  def playCards(indexes: ArrayBuffer[Int]): Unit =
    safeRun(() => state.playCards(this, indexes))

  /**
   * Attempts to discard the selected cards from the hand.
   *
   * @param indexes the indices of the cards to discard
   */
  def discardCards(indexes: ArrayBuffer[Int]): Unit =
    safeRun(() => state.discardCards(this, indexes))

  /** Evaluates the current score against the target score to check for win/loss conditions. */
  def evaluateScore(): Unit =
    safeRun(() => state.evaluateScore(this))

  /** @return true if the game has reached a terminal state (win or lose), false otherwise. */
  def isGameOver: Boolean = state.isGameOver

  /** @return true if the game has been won, false otherwise. */
  def isWin: Boolean = state.isWin

  /** @return the current game state. */
  def getState: GameState = state

  /**
   * Initializes the game by creating a shuffled deck, drawing the starting hand,
   * attaching the controller as an observer, and starting the state machine.
   */
  def initialGame(): Unit = {
    val fullDeck = createShuffledDeck()
    val initialCards = fullDeck.take(8)

    this.deck = fullDeck.drop(8)
    this.hand = new Hand(initialCards, ArrayBuffer())

    this.hand.attach(this)
    this.start()
  }

  /**
   * Draws cards from the deck to refill the player's hand up to a maximum of 8 cards.
   */
  def drawCards(): Unit = {
    if (hand != null) {
      val missingCards = 8 - hand.cards.length

      if (missingCards > 0 && deck.nonEmpty) {
        val cardsToDraw = deck.take(missingCards)

        deck --= cardsToDraw

        hand.addCards(cardsToDraw)
      }
    }
  }

  // ====================================================
  // MÉTODOS DELEGADOS PARA LA INTERFAZ GRÁFICA (MVC)
  // ====================================================

  /** Sorts the cards in the player's hand by rank value. */
  def sortHandByRank(): Unit = {
    hand.getHandCards.sortInPlaceBy(_.rank.value)
  }

  /** Sorts the cards in the player's hand by suit. */
  def sortHandBySuit(): Unit = {
    def suitToNumber(label: String): Int = label.toLowerCase match {
      case "spades" => 1
      case "hearts" => 2
      case "clubs" => 3
      case "diamonds" => 4
      case _ => 5
    }
    hand.getHandCards.sortInPlaceBy(card => suitToNumber(card.suit.label))
  }

  /** * Evaluates a subset of cards and returns the name of the best poker hand.
   * * @param selectedIndexes the indices of the selected cards
   * @return the string representation of the best poker hand
   */
  def evaluateSelectedHandName(selectedIndexes: Iterable[Int]): String = {
    if (selectedIndexes.isEmpty) return "None"
    val currentCards = hand.cards
    val selectedCards = ArrayBuffer.from(selectedIndexes.map(index => currentCards(index)))
    val tempHand = new Hand(selectedCards, ArrayBuffer())
    val bestPokerHand = HandPriority(tempHand).bestHand
    bestPokerHand.pokerHandName
  }

  /**
   * Adds the selected jokers to the hand and plays the selected cards.
   *
   * @param selectedIndexes the indices of the cards to play
   * @param selectedJokers the names of the jokers to apply
   */
  def playCardsWithSelectedJokers(selectedIndexes: Seq[Int], selectedJokers: Iterable[String]): Unit = {
    hand.getHandJokers.clear()
    val jokersToAdd = ArrayBuffer[Joker]()

    if (selectedJokers.exists(_ == "Devious")) jokersToAdd += new DeviousJoker()
    if (selectedJokers.exists(_ == "Even Steven")) jokersToAdd += new EvenSteven()
    if (selectedJokers.exists(_ == "Greedy")) jokersToAdd += new GreedyJoker()
    if (selectedJokers.exists(_ == "Scary Face")) jokersToAdd += new ScaryFace()

    hand.addJokers(jokersToAdd)

    val indexesToPlay = selectedIndexes.sorted(Ordering[Int].reverse).to(ArrayBuffer)
    playCards(indexesToPlay)
    evaluateScore()
  }

}

/**
 * Generates a complete, shuffled standard 52-card deck.
 *
 * @return an ArrayBuffer containing the shuffled cards
 */
private def createShuffledDeck() : ArrayBuffer[Card] = {
  val suits = Array(new Hearts, new Spades, new Diamonds, new Clubs)
  val ranks = Array(new Two, new Three, new Four, new Five, new Six, new Seven, new Eight, new Nine, new Ten, new Jack, new Queen, new King, new Ace)
  val deck = ArrayBuffer[Card]()

  for {
    suit <- suits
    rank <- ranks
  } deck += new Card(suit, rank)

  Random.shuffle(deck)
}
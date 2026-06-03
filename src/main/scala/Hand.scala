package cl.uchile.dcc
import scala.collection.mutable.ArrayBuffer
import Jokers.*


/**
 * Represents a player's hand in the game.
 *
 * A Hand contains the set of cards currently held by the player,
 * along with any active jokers that may affect scoring or behavior.
 *
 * This class manages the state of the hand, including adding, removing,
 * and playing cards or jokers. It does not implement hand evaluation logic.
 *
 * @param handCards  the collection of cards in the hand
 * @param handJokers the collection of jokers associated with the hand
 */

class InvalidHand(s: String) extends Exception(s)


class Hand(var handCards: ArrayBuffer[Card], var handJokers: ArrayBuffer[Joker]) {

  private val _cards = handCards
  private val _jokers = handJokers

  if (_cards.length > 8){
    throw new InvalidHand(
      s"Limit exceeded: The base hand cannot start with more than 8 cards."
    )
  }

  if (_jokers.length > 2) {
    throw new InvalidHand(
      s"Limit exceeded: The base hand cannot start with more than 2 Jokers."
    )
  }
  /**
   * Returns the current cards in the hand as an immutable list.
   */
  def cards: List[Card] = _cards.toList

  /**
   * Returns the current jokers in the hand as an immutable list.
   */
  def jokers: List[Joker] = _jokers.toList

  /**
   * Adds multiple cards to the hand.
   *
   * @param newCards the cards to be added
   */
  def addCards(newCards: ArrayBuffer[Card]): Unit = {
    if (_cards.length + newCards.length > 8){
      throw new InvalidHand(
        s"Limit exceeded: You have ${_cards.length} cards and you are trying to add ${newCards.length}. The maximum allowed is 8"
      )
    }
    _cards ++= newCards
  }

  /**
   * Adds multiple jokers to the hand.
   *
   * @param newJokers the jokers to be added
   */
  def addJokers(newJokers: ArrayBuffer[Joker]): Unit = {
    if (_jokers.length + newJokers.length > 2) {
      throw new InvalidHand(
        s"Limit exceeded: You have ${_jokers.length} jokers and you are trying to add ${newJokers.length}. The maximum allowed is 2"
      )
    }
    
    _jokers ++= newJokers
  }

  /**
   * Removes cards from the hand based on their indices.
   *
   * Indices are processed in descending order to avoid shifting issues
   * caused by removing elements from a mutable collection.
   *
   * @param indexes the positions of the cards to be removed
   */
  def discardCards(indexes: ArrayBuffer[Int]): Unit = {
    for (i <- indexes) {
      if (i < 0 || i >= _cards.length) {
        throw new InvalidHand(s"Invalid index: the hand does not have an index card $i")
      }
    }
    val sortedIndexes = indexes.sorted.reverse

    for (i <- sortedIndexes) {
      _cards.remove(i)
    }
  }

  /**
   * Removes jokers from the hand based on their indices.
   *
   * Indices are processed in descending order to ensure correct removal
   * without affecting the positions of remaining elements.
   *
   * @param indexes the positions of the jokers to be removed
   */
  def discardJokers(indexes: ArrayBuffer[Int]): Unit = {
    for (i <- indexes) {
      if (i >= _jokers.length) {
        throw new InvalidHand(s"Invalid index: the hand does not have an index joker $i")
      }
    }
    val sortedIndexes = indexes.sorted.reverse

    for (i <- sortedIndexes) {
      _jokers.remove(i)
    }
  }
  /**
   * Plays a subset of cards from the hand.
   *
   * Given a list of indices, this method:
   * 1. Collects the corresponding cards
   * 2. Removes them from the hand
   * 3. Returns the selected cards
   *
   * @param indexes the positions of the cards to be played
   * @return the cards that were played
   */
  def playHand(indexes: ArrayBuffer[Int]): ArrayBuffer[Card] = {
    if (indexes.length > 5){
      throw new InvalidHand("Limit exceeded: you cannot play more than 5 cards")
    }
    val playedCards = ArrayBuffer[Card]()
    
    for (i <- indexes) {
      playedCards += _cards(i)
    }

    discardCards(indexes)

    playedCards
  }
}
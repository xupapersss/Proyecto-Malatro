package cl.uchile.dcc
import scala.collection.mutable.ArrayBuffer
import Jokers._

/**
 * Represents a player's hand in the game.
 *
 * A Hand contains the set of cards currently being played,
 * along with any active jokers that may affect scoring or behavior.
 *
 * This class acts as a container for game data and does not
 * implement evaluation logic directly.
 *
 * @param handCards  the collection of cards in the hand
 * @param handJokers the collection of jokers associated with the hand
 */
class Hand(var handCards: ArrayBuffer[Card], var handJokers: ArrayBuffer[Joker]) {

  private val _cards = handCards
  private val _jokers = handJokers

  def cards: List[Card] = _cards.toList

  def jokers: List[Joker] = _jokers.toList

  def addCards(newCards: ArrayBuffer[Card]): Unit = {
    _cards ++= newCards
  }

  def addJokers(newJokers: ArrayBuffer[Joker]): Unit = {
    _jokers ++= newJokers
  }

  def discardCards(indexes: ArrayBuffer[Int]): Unit = {
    val sortedIndexes = indexes.sorted.reverse

    for (i <- sortedIndexes) {
      _cards.remove(i)
    }
  }
}
  
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
 * @param cards  the collection of cards in the hand
 * @param jokers the collection of jokers associated with the hand
 */

class Hand(var cards: ArrayBuffer[Card], var jokers: ArrayBuffer[Joker]) {
  
}

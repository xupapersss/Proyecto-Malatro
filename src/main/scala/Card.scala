package cl.uchile.dcc
import Suit._
import Rank._

class Card (val suit:Suit, val rank: Rank) {
  def chips: Int = rank.value
}

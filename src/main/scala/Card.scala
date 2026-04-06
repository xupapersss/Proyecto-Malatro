package cl.uchile.dcc

import Suit.

import Rank.

class Card (val suit:Suit, val rank: Rank) {
  def chips: Int = rank.value
}

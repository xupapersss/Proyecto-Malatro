package cl.uchile.dcc
package Rank

trait Category {
  def label: String
}

class Even extends Category {
  val label = "Even"
}

class Odd extends Category {
  val label = "Odd"
}

class FaceCard extends Category {
  val label = "Face Card"
}
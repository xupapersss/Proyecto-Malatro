package cl.uchile.dcc
package view

import controller.Controller

object main {
  def main(args: Array[String]): Unit = {
    println("¡Welcome to Malatro!")

    val gameController = new Controller

    gameController.initialGame()

    println("Game started")

  }
}

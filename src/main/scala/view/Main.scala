package cl.uchile.dcc
package view

import controller.Controller
import scala.io.StdIn.readLine


object Main {
  def main(args: Array[String]): Unit = {
    println("¡Welcome to Malatro!")

    val gameController = new Controller

    gameController.targetScore = playerTargetScore()

    gameController.initialGame()

    println("Game started")

    while (!gameController.isGameOver) {

    }

    if (gameController.isWin) {
      println("¡You Win!")
    }


  }
}

private def playerTargetScore(): Int = {
  var score = 0
  while (score == 0){
    print(s"Enter your target score: ")
    val input = readLine().trim
    score = input.toInt

  }
  score
}

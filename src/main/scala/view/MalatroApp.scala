package cl.uchile.dcc
package view

import scalafx.scene.image.{Image, ImageView}
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, StackPane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import rank.*
import suit.*

import cl.uchile.dcc.controller.Controller
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.text.Text


object MalatroApp extends JFXApp3 {

  def createCardView(card: Card): StackPane = {

    val rankStr = card.rank match {
      case _: Ace   => "Ace"
      case _: Two   => "2"
      case _: Three => "3"
      case _: Four  => "4"
      case _: Five  => "5"
      case _: Six   => "6"
      case _: Seven => "7"
      case _: Eight => "8"
      case _: Nine  => "9"
      case _: Ten   => "10"
      case _: Jack  => "Jack"
      case _: Queen => "Queen"
      case _: King  => "King"
      case _        => card.rank.getRankName
    }

    val fileName = s"${rankStr}_of_${card.suit.label}.png"
    val imagePath = getClass.getResource(s"/cards/$fileName")

    val imageView = if (imagePath != null) {
      new ImageView(new Image(imagePath.toString)) {
        fitWidth = 100
        preserveRatio = true
      }
    } else {
      println(s"⚠️ Error: Image not found in /cards/$fileName")
      new ImageView()
    }

    new StackPane {
      children = List(imageView)
    }
  }

  override def start(): Unit = {
    val menuBox = new VBox {
      spacing = 20
      alignment = Pos.Center

      val title = new Text("Select difficulty:") {
        fill = Color.White
        style = "-fx-font-size: 24px; -fx-font-weight: bold;"
      }

      val btnEasy = new Button("Easy (300)") {
        style = "-fx-font-size: 18px; -fx-min-width: 200px;"
        onAction = _ => stage.scene = createGameScene(300)
      }

      val btnMedium = new Button("Medium (1000)") {
        style = "-fx-font-size: 18px; -fx-min-width: 200px;"
        onAction = _ => stage.scene = createGameScene(1000)
      }

      val btnHard = new Button("Hard (5000)") {
        style = "-fx-font-size: 18px; -fx-min-width: 200px;"
        onAction = _ => stage.scene = createGameScene(5000)
      }

      children = List(title, btnEasy, btnMedium, btnHard)

      translateX = 380
      translateY = 250
    }

    val menuScene = new Scene(1000, 700) {
      fill = Color.Grey
      content = List(menuBox)
    }

    stage = new JFXApp3.PrimaryStage {
      title = "Malatro"
      scene = menuScene
    }
  }

  def createGameScene(selectedScore: Int): Scene = {
    val gameController = new Controller()
    gameController.targetScore = selectedScore
    gameController.initialGame()

    println(s"Starting game with Target Score: $selectedScore")

    val handBox = new HBox {
      spacing = 10
      padding = Insets(20)
      alignment = Pos.Center
      children = gameController.hand.cards.map(card => createCardView(card))
      translateY = 300
    }

    new Scene(1000, 700) {
      fill = Color.Gray
      content = List(handBox)
    }
  }
}
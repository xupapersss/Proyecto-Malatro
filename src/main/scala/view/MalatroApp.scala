package cl.uchile.dcc
package view

import scalafx.scene.image.{Image, ImageView}
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, StackPane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import rank.*
import suit.*

import cl.uchile.dcc.controller.Controller
import scalafx.geometry.Insets


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

    val gameController = new Controller()
    gameController.targetScore = 5000
    gameController.initialGame()

    val handBox = new HBox {
      spacing = 10
      padding = Insets(20)

      val cardViews = gameController.hand.cards.map(card => createCardView(card))
      children = cardViews

      translateY = 300
    }

    stage = new JFXApp3.PrimaryStage {
      title = "Malatro"
      width = 1000
      height = 700
      scene = new Scene {
        fill = Color.Gray
        content = List(handBox)
      }
    }
  }
}
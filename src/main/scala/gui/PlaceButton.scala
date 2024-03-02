package gui

import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.geometry.Pos
import game.Player
import scalafx.geometry.Insets
import game.TowerDefenceGame
import utils.GridPos
import scala.collection.mutable.ArrayBuffer

class PlaceButton(player: Player) extends HBox {
    alignment = Pos.Center
    padding = Insets(0, 25, 40, 20)

    val button = new Button("Place a purchased Tower") {
      onAction = _ => {
        // Define the action to purchase a new tower
        println("Placing a new tower...")
      }
        style = "-fx-font-size: 12pt; " +
          "-fx-background-color: #d3d3d3; " +
          "-fx-border-color: #333333; " +
          "-fx-border-width: 2px; " +
          "-fx-border-radius: 4; " +
          "-fx-padding: 5;"
    }
    children.add(button)
  }


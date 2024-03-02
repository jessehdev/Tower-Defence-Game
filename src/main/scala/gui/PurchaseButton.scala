package gui

import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.geometry.Pos
import game.Player
import scalafx.geometry.Insets

object PurchaseButton {
  def apply(player: Player): HBox = new HBox {
    alignment = Pos.Center
    padding = Insets(20, 20, 20, 20)

    val button = new Button("Purchase a new Tower") {
      // Set up the event handler for button action (click)
      onAction = _ => {
        // Define the action to purchase a new tower
        println("Purchasing a new tower...")
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
}

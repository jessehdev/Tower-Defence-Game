package gui

import scalafx.scene.layout.{VBox, HBox}
import scalafx.geometry.Pos
import game.{Player, TowerDefenceGame}
import scalafx.geometry.Insets
import scalafx.scene.control._
import scalafx.scene.text._
import scala.collection.mutable.ArrayBuffer
import utils.GridPos
import towers.Tower
import scalafx.event.ActionEvent
import scalafx.Includes._

// THIS CODE IS NOT CURRENTLY USED IN THE APPLICATION
class TowerInventory(game: TowerDefenceGame) extends VBox {
    alignment = Pos.Center
    padding = Insets(0, 28, 0, 5)
    spacing = 10
    
    var available = game.gameBoard.available
    val posComboBox = new ComboBox(available.toList)

    var placeable = game.placeableTowers
    val towerComboBox = new ComboBox(placeable.toList)

    val placeButton = new Button("Place a purchased tower") {
      style = "-fx-font-size: 12pt; " +
         "-fx-background-color: #d3d3d3; " +
         "-fx-border-color: #333333; " +
         "-fx-border-width: 2px; " +
         "-fx-border-radius: 4; " +
         "-fx-padding: 5;" 
    }     

    placeButton.onAction = (event: ActionEvent) => {
        println("Trying to place tower")
        (towerComboBox.value.value, posComboBox.value.value) match 
          case (tower: Tower, position: GridPos) =>
            try 
              game.gameBoard.placeTower(tower, position)  
            catch 
            case e: Exception => println(s"Error placing tower: ${e.getMessage}")
          case _ => println("Tower or position not selected")
      }
    
    val chooseContainer = new HBox {
      alignment = Pos.Center
      spacing = 30

      val towersLabel = new Label()
        towersLabel.text = "Choose tower"
        towersLabel.font = Font.font("Arial", 12)
    
      val placeLabel = new Label()
        placeLabel.text = "Choose position"
        placeLabel.font = Font.font("Arial", 12)
      children = Array(towersLabel, placeLabel)
    }

    val optionContainer = new HBox {
      alignment = Pos.Center
      spacing = 60
      children = Array(posComboBox, towerComboBox)
    }

    var towerContainer = new VBox {
      alignment = Pos.Center
      spacing = 15
    }
    children = Array(placeButton, chooseContainer, optionContainer, towerContainer)
}

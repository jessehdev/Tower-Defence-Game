package gui

import scalafx.scene.control._
import scalafx.scene.layout.{VBox, HBox}
import scalafx.geometry.Pos
import game.TowerDefenceGame
import scalafx.geometry.Insets
import scalafx.event.ActionEvent
import towers._
import utils.GridPos
import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scala.compiletime.ops.double
import scala.collection.mutable.ArrayBuffer
import scalafx.scene.control.Alert.AlertType

class UpgradeView(game: TowerDefenceGame) extends VBox {
    alignment = Pos.Center
    padding = Insets(10, 20, 10, 20)

    val towerPositions = ObservableBuffer[GridPos]()
    for i <- game.towers do
      towerPositions += i.position
    val towerComboBox = new ComboBox(towerPositions)

    val button = new Button("Upgrade a Tower") {
      style = "-fx-font-size: 12pt; " +
          "-fx-background-color: #d3d3d3; " +
          "-fx-border-color: #333333; " +
          "-fx-border-width: 2px; " +
          "-fx-border-radius: 4; " +
          "-fx-padding: 5;"
    }

    button.onAction = (event: ActionEvent) => {
      println("Trying to upgrade a tower")
      towerComboBox.value.value match
        case pos: GridPos =>
            game.gameState.player.upgradeTower(pos)
            showUpgradeConfirmation(pos)
        case _ => println("Cannot upgrade")  
      }

    def showUpgradeConfirmation(pos: GridPos) = 
      game.towers.find(_.position == pos).foreach( tower =>
        new Alert(AlertType.Information) {
          title = "Tower Upgrade Successful"
          headerText = s"Tower at position $pos upgraded!"
          contentText = s"Current Level: ${tower.level}, Current Damage: ${tower.damage}"
        }.showAndWait()
      )

    val boxContainer = new VBox {
      alignment = Pos.Center
      padding = Insets(10,0,0,0)
      val comboBox = towerComboBox
      children.add(comboBox)
    }
    
    children = Array(button, boxContainer)
  }
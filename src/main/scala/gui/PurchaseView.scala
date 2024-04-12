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
import scalafx.scene.control.Alert.AlertType

/*
 * A view for purchasing a tower
 * Checks available positions for placing the towers
 * Enables purchasing & placing towers to those positions 
 * Shows error messages if errors encountered
 */
class PurchaseView(game: TowerDefenceGame, upgradeView: UpgradeView) extends VBox {
    alignment = Pos.Center
    padding = Insets(10, 20, 10, 20)

  //available tower types
    val comboBox = new ComboBox(List("Basic","SplashDamage"))
  //available positions (GridPos)
    var available = game.gameBoard.available
    var posComboBox = new ComboBox(available.toList)

    val purchaseButton = new Button("Purchase a new Tower") {
      style = "-fx-font-size: 12pt; " +
          "-fx-background-color: #d3d3d3; " +
          "-fx-border-color: #333333; " +
          "-fx-border-width: 2px; " +
          "-fx-border-radius: 4; " +
          "-fx-padding: 5;"
    }

    purchaseButton.onAction = (event: ActionEvent) => {
      println("Trying to purchase tower")
      (comboBox.value.value, posComboBox.value.value) match
        case ("Basic", pos: GridPos) =>
            game.gameState.player.purchaseTower(Basic(pos), pos)
        case ("SplashDamage", pos: GridPos) => 
            game.gameState.player.purchaseTower(SplashDamage(pos), pos)
        case _ => println("Tower or position not selected")
      updatePurchasing()  
      upgradeView.updateUpgradable()
      }
    
    val chooseBoxes = new HBox {
      alignment = Pos.Center
      spacing = 30
      padding = Insets(10,0,0,0)
      val towerBox = comboBox
      val posBox = posComboBox
      children = Array(towerBox, posBox)
    }

    def showPurchasingError(t: Tower) =
      new Alert(AlertType.Error) {
          title = "Cannot purchase tower"
          headerText = s"Not enough resources"
          contentText = s"Current Resources : ${game.gameState.player.resources}, Cost : ${t.cost}"
        }.showAndWait()

    def updatePurchasing() =
      game.gameBoard.updateAvailable()
      posComboBox.items = ObservableBuffer(available.toList: _*)
      //posComboBox.getSelectionModel.clearSelection()

    children = Array(purchaseButton, chooseBoxes)
  }

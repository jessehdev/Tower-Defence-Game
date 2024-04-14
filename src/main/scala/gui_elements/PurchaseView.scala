package gui_elements

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
  // Cost values hardcoded :(
    val comboBox = new ComboBox(List("Basic, cost: 100","SplashDamage, cost: 150"))
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
        case ("Basic, cost: 100", pos: GridPos)        =>
          //purchasetower returns boolean
            if !game.gameState.player.purchaseTower(Basic(game, pos), pos) then
              showPurchasingError()
        case ("SplashDamage, cost: 150", pos: GridPos) => 
            if !game.gameState.player.purchaseTower(SplashDamage(game, pos), pos) then
              showPurchasingError()
        case _                                         => 
            new Alert(AlertType.Error) {
            title = "Cannot purchase tower"
            headerText = s"Select a tower type and a position below"
          }.showAndWait()

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

    def showPurchasingError() =
      new Alert(AlertType.Error) {
          title = "Cannot purchase tower"
          headerText = s"Not enough resources"
          contentText = s"Current Resources : ${game.gameState.player.resources}"
        }.showAndWait()

    def updatePurchasing() =
      game.gameBoard.updateAvailable()
      posComboBox.items = ObservableBuffer(available.toList: _*)
      //posComboBox.getSelectionModel.clearSelection()

    // call the method so it is possible to purchase a tower before the game starts
    updatePurchasing()

    children = Array(purchaseButton, chooseBoxes)
  }

package gui_elems

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

/*
 * A view for upgrading towers
 * Consists of a combobox for checking towers for upgration and
 * a button for actually upgrading the tower
 * After upgrading user gets a confirmation or an error if unsuccesful
 */
class UpgradeView(game: TowerDefenceGame) extends VBox {
    alignment = Pos.Center
    padding = Insets(10, 20, 10, 20)

    //checks where towers are currently placed and adds the positions to a combobox
    val towerPositions = ObservableBuffer[GridPos]()
    for i <- game.towers do
      towerPositions += i.position
    val towerComboBox = new ComboBox(towerPositions)

    val upgradeTowerButton = new Button("Upgrade a Tower") {
      style = "-fx-font-size: 12pt; " +
          "-fx-background-color: #d3d3d3; " +
          "-fx-border-color: #333333; " +
          "-fx-border-width: 2px; " +
          "-fx-border-radius: 4; " +
          "-fx-padding: 5;"
    }

    upgradeTowerButton.onAction = (event: ActionEvent) => {
      towerComboBox.value.value match
        case pos: GridPos => 
            val tower = game.towers.find(_.position == pos).get
            if tower.level < tower.maxLevel then
              // upgradetower returns boolean 
              if game.gameState.player.upgradeTower(pos) then
                showUpgradeConfirmation(pos)
              else
                showUpgradeError(pos)
            else
              //If max level, can't upgrade and must notify user
              showMaxLevelError()
        case null          =>
            new Alert(AlertType.Error) {
           title = "Cannot purchase tower"
           headerText = s"Select the position of the tower"
           }.showAndWait() 
      }
// this is shown if upgrading was succesful
    def showUpgradeConfirmation(pos: GridPos) = 
      game.towers.find(_.position == pos).foreach( tower =>
        new Alert(AlertType.Information) {
          title = "Tower Upgrade Successful"
          headerText = s"Tower at position $pos upgraded!"
          contentText = s"Current Level: ${tower.level}, Current Damage : ${tower.damage}"
        }.showAndWait()
      )

/*
* This is shown if upgrading wasn't succesful
* Finds the tower in the position given, gives stats why upgrading
* didn't work
*/
    def showUpgradeError(pos: GridPos) =
      val towers = ArrayBuffer[Tower]()
      for i <- game.towers do
        if i.position == pos then
          towers += i
      val t = towers.head
      if game.gameState.player.resources < t.cost then 
        new Alert(AlertType.Error) {
          title = "Cannot upgrade tower"
          headerText = "Not enough resources"
          contentText = s"Resources: ${game.gameState.player.resources}, Cost: ${t.upgradeCost}"
    }.showAndWait()  

    // showing upgrading error if tower has max level
    def showMaxLevelError() =
       new Alert(AlertType.Warning) {
          title = "Cannot upgrade tower"
          headerText = "Tower Max Level Reached"
    }.showAndWait()

// ChatGPT helped with clearing the combobox (look for the last line in this method)
    def updateUpgradable() =
      towerPositions.clear()
      for i <- game.towers do
        towerPositions += i.position
      towerComboBox.getSelectionModel.clearSelection()  

    val boxContainer = new VBox {
      alignment = Pos.Center
      padding = Insets(10,0,0,0)
      val comboBox = towerComboBox
      children.add(comboBox)
    }
    
    children = Array(upgradeTowerButton, boxContainer)
  }
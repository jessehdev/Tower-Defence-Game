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
import scala.compiletime.ops.double
import scala.collection.mutable.ArrayBuffer
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image

/*
 * A button object for starting and pausing the game from the gui 
 */

class StartGameView(game: TowerDefenceGame, pView: PurchaseView) extends VBox {
    padding = Insets(10, 20, 10, 20)
    var flag = true
    val startGameButton = new Button("") {
        //updates available positions for purchasing towers and starts the timer from the backend
        onAction = (event: ActionEvent) => {
            if flag then
                pView.updatePurchasing()
                game.startTimer()
                graphic_=(new ImageView(new Image("file:src/main/resources/pause-button.png")))
                flag = false
            else
                game.stopTimer()
                graphic_=(new ImageView(new Image("file:src/main/resources/playButton.png")))
                flag = true
        } 

        graphic_=(new ImageView(new Image("file:src/main/resources/playButton.png")))
        }
    children = Array(startGameButton)
}
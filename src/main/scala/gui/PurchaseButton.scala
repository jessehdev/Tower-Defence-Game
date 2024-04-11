/*
package gui

import scalafx.scene.control._
import scalafx.scene.layout.VBox
import scalafx.geometry.Pos
import game.Player
import scalafx.geometry.Insets
import scalafx.event.ActionEvent
import towers._
import utils.GridPos
import scalafx.Includes._

/*
 * THIS ENTIRE FILE IS LEGACY CODE THAT I MIGHT WANT TO 
 * WORK ON LATER ON, IT IS NOT CURRENTLY USED IN THE GAME IN ANY WAY
 */

object PurchaseButton {
  def apply(player: Player)= new VBox {
    alignment = Pos.Center
    padding = Insets(20, 20, 20, 20)

    val comboBox = new ComboBox(List("Basic","SplashDamage"))

    val button = new Button("Purchase a new Tower") {
      style = "-fx-font-size: 12pt; " +
          "-fx-background-color: #d3d3d3; " +
          "-fx-border-color: #333333; " +
          "-fx-border-width: 2px; " +
          "-fx-border-radius: 4; " +
          "-fx-padding: 5;"
    }
/*
    button.onAction = (event: ActionEvent) => {
      println("Trying to purchase tower")
      comboBox.value.value match
        case "Basic" =>
          try  
            player.purchaseTower(Basic(GridPos(2,2)))
          catch 
          case e: Exception => println(s"Error placing tower: ${e.getMessage}")
        case "SplashDamage" => 
          try
            player.purchaseTower(SplashDamage(GridPos(2,2)))
          catch 
          case e: Exception => println(s"Error placing tower: ${e.getMessage}")
        case _ => println("Tower or position not selected")
      }
*/
    children = Array(button, comboBox)
  }
}
*/
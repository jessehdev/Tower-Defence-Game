package gui_elements

import scalafx.scene.control.Label
import scalafx.scene.layout.{HBox}
import scalafx.scene.text.{Font, FontWeight, FontPosture}
import scalafx.geometry.Pos
import game._
import scalafx.geometry.Insets

/*
 * Statusbar is the part of the gui that shows the "stats" of the game
 * It is found at the top of the window
 */
class StatusBar(state: GameState) extends HBox {
  padding = Insets(10, 0, 0, 0)
  spacing = 90
  alignment = Pos.Center
  
  var resourcesLabel = new Label()
  var enemiesKilledLabel = new Label()
  var wavesLeftLabel = new Label()
  
  updateLabels() // Initial update

  def updateLabels() =  
    resourcesLabel.text = s"Resources: ${state.player.resources}"
    resourcesLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 22)

    enemiesKilledLabel.text = s"Enemies Killed: ${state.enemiesKilled}"
    enemiesKilledLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 22)

    wavesLeftLabel.text = s"Waves Left: ${state.wavesLeft}"
    wavesLeftLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 22)
  end updateLabels

  children = Seq(resourcesLabel, enemiesKilledLabel, wavesLeftLabel)
}

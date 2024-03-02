package gui

import scalafx.scene.control.Label
import scalafx.scene.layout.{HBox}
import scalafx.scene.text.{Font, FontWeight, FontPosture}
import scalafx.geometry.Pos
import game._
import scalafx.geometry.Insets

class StatusBar(state: GameState) extends HBox {
  padding = Insets(10, 0, 0, 0)
  spacing = 90
  alignment = Pos.Center
  
  val resourcesLabel = new Label()
  val enemiesKilledLabel = new Label()
  val wavesLeftLabel = new Label()
  
  updateLabels() // Initial update

  def updateLabels(): Unit = {
    resourcesLabel.text = s"Resources: ${state.player.resources}"
    resourcesLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 22)

    enemiesKilledLabel.text = s"Enemies Killed: ${state.enemiesKilled}"
    enemiesKilledLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 22)

    wavesLeftLabel.text = s"Waves Left: ${state.wavesLeft}"
    wavesLeftLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 22)
  }

  children = Seq(resourcesLabel, enemiesKilledLabel, wavesLeftLabel)
}

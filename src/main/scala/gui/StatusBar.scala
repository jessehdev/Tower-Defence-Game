package gui

import scalafx.scene.control.Label
import scalafx.scene.layout.HBox
import game.{Player, GameState}
import scalafx.geometry.Insets
import scalafx.scene.text._
import scalafx.scene.paint.Color

object StatusBar {
  def apply(player: Player, state: GameState): HBox = new HBox {
    prefHeight = 100
    padding = Insets(40, 40, 40, 40)
    spacing = 120

    val resourcesLabel = Label(s"Resources: ${player.resources}")
      resourcesLabel.textFill = Color.Red
      resourcesLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 16)

    val enemiesKilledLabel = Label(s"Enemies Killed: ${state.enemiesKilled}")
      enemiesKilledLabel.textFill = Color.Red
      enemiesKilledLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 16)

    val wavesLeftLabel = Label(s"Waves Left: ${state.wavesLeft}")
      wavesLeftLabel.textFill = Color.Red
      wavesLeftLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 16)

    children = Array(resourcesLabel, enemiesKilledLabel, wavesLeftLabel)

  }
}

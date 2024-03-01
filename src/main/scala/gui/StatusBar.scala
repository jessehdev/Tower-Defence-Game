package gui

import scalafx.scene.control.Label
import scalafx.scene.layout.HBox
import game.{Player, GameState}
import scalafx.geometry.Insets
import scalafx.scene.text._
import scalafx.scene.paint.Color
import scalafx.geometry.Pos

object StatusBar {
  def apply(player: Player, state: GameState): HBox = new HBox {
   // prefHeight = 100
    padding = Insets(10, 0, 0, 0)
    spacing = 90
    alignment = Pos.Center

    val resourcesLabel = Label(s"Resources: ${player.resources}")
      resourcesLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 22)

    val enemiesKilledLabel = Label(s"Enemies Killed: ${state.enemiesKilled}")
      enemiesKilledLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 22)

    val wavesLeftLabel = Label(s"Waves Left: ${state.wavesLeft}")
      wavesLeftLabel.font = Font.font("Arial", FontWeight.Bold, FontPosture.Italic, 22)

    children = Array(resourcesLabel, enemiesKilledLabel, wavesLeftLabel)

  }
}

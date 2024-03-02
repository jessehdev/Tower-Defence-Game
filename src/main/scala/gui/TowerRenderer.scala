package gui

import game.{TowerDefenceGame}
import utils.{GridPos}
import towers._
import scalafx.scene.layout.{GridPane, StackPane}
import scalafx.scene.shape.Polygon
import scalafx.scene.paint.Color

class TowerRenderer(game: TowerDefenceGame, gameBoard: GridPane) {
 // val cellSize = gameBoard.cellSize // Assuming cellSize is accessible

  // Function to create an octagon shape for a tower
  private def createOctagon(centerX: Double, centerY: Double, size: Double): Polygon = {
    val octagon = new Polygon()
    // Calculate the points for the octagon based on the center position and size
    val numSides = 8
    val angleStep = 2 * Math.PI / numSides
    for (i <- 0 until numSides) 
      octagon.points.addAll(
        (centerX + size * Math.cos(i * angleStep)),
        (centerY + size * Math.sin(i * angleStep))
      )
    
    octagon
  }

  // Function to render towers on the game board
  def renderTowers(): Unit = 
    game.towers.foreach(tower =>
      val towerNode = new StackPane {
        val octagon = createOctagon(48, 48, 24) 
        octagon.fill = tower match {
          case _: Basic => Color.web("#ed92fd") // ("#da1afb")  
          case _: SplashDamage => Color.web("#B70404") 
        }
        children.add(octagon)
      }
      GridPane.setConstraints(towerNode, tower.position.x, tower.position.y)
      gameBoard.children.add(towerNode)
    )
}

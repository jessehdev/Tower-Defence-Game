package gui_elems

import game.{TowerDefenceGame}
import utils.{GridPos}
import towers._
import scalafx.scene.layout.{GridPane, StackPane}
import scalafx.scene.shape.{Polygon, Line}
import scalafx.scene.paint.Color
import enemies.Enemy
import scalafx.scene.shape.Line
import scalafx.animation.PathTransition
import scalafx.util.Duration
import scalafx.scene.shape.Path
import scalafx.scene.shape.MoveTo
import scalafx.scene.shape.LineTo
import scalafx.animation.PauseTransition
import scalafx.application.Platform
import scalafx.animation.TranslateTransition
import scalafx.util.Duration
import scalafx.scene.layout.BorderPane
import scalafx.scene.shape.Circle
import scalafx.beans.property.ReadOnlyDoubleProperty

/*
 * TowerRendered handles the graphical representation and
 * rendering of the towers including their shooting
 */
class TowerRenderer(game: TowerDefenceGame, gameBoard: GameBoardView, gameView: BorderPane, topContainerHeight: ReadOnlyDoubleProperty) {
  //val cellSize = 100
  val cellSize = gameBoard.cellSize
  /* 
   *Function to create an octagon shape for a tower
   *This function has been generated by ChatGPT
  */ 
  def createOctagon(centerX: Double, centerY: Double, size: Double) = 
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
  end createOctagon  

  def animateShooting(tower: Tower, target: Enemy) = 
    Platform.runLater {
    // Creates a circle to represent the ammunition to be shot
    val ammunition = new Circle {
      // consider using centerX and centerY for initial positions
      radius = 3  // Set the radius of the projectile
      fill = Color.web("00008B") // dark blue
    }

    // Add the projectile to the game view
    gameView.children.add(ammunition)
   
    val transition = new TranslateTransition {
     node = ammunition
     /*
      * topContainerHeight is the height where are the stats and the buttons for 
      * updating & upgrading the towers. target.offSetX/Y is a  random number between
      * (-20,20) generated in the backend (Enemy.scala) to achieve randomness
      * in enemy positions within a single grid
      */
     fromX = tower.position.x * cellSize + cellSize / 2 
     fromY = tower.position.y * cellSize + topContainerHeight.toDouble + cellSize / 2
     toX = target.position.x * cellSize + cellSize / 2 + target.offsetX
     toY = target.position.y * cellSize + topContainerHeight.toDouble + cellSize / 2 + target.offsetY
     duration = Duration(200) // Adjust time in milliseconds, if changed, also change tower classes where Thread.sleep is used
     cycleCount = 1
     onFinished = _ => gameView.children.remove(ammunition)
    }
    transition.play()
  }
  end animateShooting
  /*  
   * Function to render towers on the game board
   */
  def renderTowers() = 
    game.towers.foreach(tower =>
      val towerNode = new StackPane {
        val octagon = createOctagon(38, 38, 20) 
        octagon.fill = tower match {
          case _: Basic => Color.web("#ed92fd") // ("#da1afb")  
          case _: SplashDamage => Color.web("#B70404") 
        }
        children.add(octagon)
      }
      GridPane.setConstraints(towerNode, tower.position.x, tower.position.y)
      gameBoard.children.add(towerNode)
    )
  end renderTowers
}
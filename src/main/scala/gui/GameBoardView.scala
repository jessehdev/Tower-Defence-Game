package gui

import game._
import gridcells._
import gui._
import scalafx.scene.layout.GridPane
import scalafx.scene.shape.{Rectangle}
import scalafx.scene.paint.Color

/* 
 * GameBoardView is a view made for the gameboard, which consists of GridCells
 */
class GameBoardView(game: TowerDefenceGame, gameState: GameState) extends GridPane {
 // val cellSize = 40
  val gridPaneSize = 850
  val gridWidth = game.grid.length
  val gridHeight = game.grid.length
  //scaling the cellsize
  val cellSize = gridPaneSize / gridWidth.toDouble

  prefWidth = gridPaneSize
  prefHeight = gridPaneSize

//renders the gameboard
  def renderBoard() =   
    val gridCellView = new GridCellView
    // Initializing the grid
    for (x <- 0 until gridWidth; y <- 0 until gridHeight) 
      val cell = game.grid(x)(y) 
      val rectangle = new Rectangle {
        //binding would be <==
        width = cellSize
        height = cellSize
        fill = gridCellView.determineCellColor(cell) 
        stroke = Color.Black
      }
      add(rectangle, x, y)

  end renderBoard
}
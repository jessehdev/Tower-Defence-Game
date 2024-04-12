package game

import gridcells.{GridCell, SceneryCell}
import utils.{GridPos}
import utils.{Constants}
import towers.{Tower}
import game.{TowerDefenceGame}
import game.{TowerException}
import scala.collection.mutable.ArrayBuffer
import scalafx.collections.ObservableBuffer

/* 
 * This file represent the GameBoard in the Tower defence game
 * The gameboard is essentially a 2D array consisting of GridCells
 * The actual 2D array is the variable "grid" which is derived from
 * configuration files
 */

class GameBoard(game: TowerDefenceGame) {
  /*  
   *These are legacy variables I might want to use later on
  
  val width = game.constants.boardWidth
  val height = game.constants.boardHeight
  val size = width * height
  */
 
  val enemyPath = game.enemyPath
  var grid = game.grid

  /* 
  * The variable here and the method below it is used in the gui
  * to render available position options for tower placement.
  * The method uses some variables that can be found from the GridCells -folder  
  */
  var available = ObservableBuffer[GridPos]() 
  
  def updateAvailable() =
    available.clear()
    grid.foreach(_.foreach( cell =>
      if cell.canPlaceTower && !cell.hasTower(game.towers) then
        available += cell.gridPos))
     // println(s"available ${available}")
  end updateAvailable 

  /* 
  * The method below checks if
  * 1) the GridCell in question is one you can place a tower on
  * 2) a tower already exists in the position
  */

  def placeTower(tower: Tower, gridPos: GridPos): Boolean = 
    if ( grid(gridPos.x)(gridPos.y).canPlaceTower && !game.towers.exists(_.position == gridPos) )then
      game.towers += tower
      true
    else 
      throw TowerException(
        s"Cannot place tower, check if the gridCell is already populated or whether it is a towerCell or not"
      )
      false
  end placeTower 

/* 
 * Once again, this is some fully working legacy code 
 * that I might want to use later if elaborating on
 * the development of the game

  def destroyTower(gridPos: GridPos): Boolean = 
    if grid(gridPos.x)(gridPos.y).hasTower(game.towers) then 
      game.towers = game.towers.filter( _.position != gridPos)
      true
    else 
      false
  end destroyTower
 */ 
} 
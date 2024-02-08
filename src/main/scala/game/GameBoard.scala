package game

import gridcells.{GridCell, SceneryCell}
import utils.{GridPos}
import utils.{Constants}
import towers.{Tower}
import game.{TowerDefenceGame}
import game.{TowerException}

class GameBoard(game: TowerDefenceGame) {
  val width = game.constants.boardWidth
  val height = game.constants.boardHeight
  val size = width * height
 
 /*  
  * initializes a grid consisting of scenery cells
  * use later on, when reading JSON files
  * might be unnecessary if grid data is given in JSON

  var grid: Array[Array[GridCell]] = Array.tabulate(width, height)( (i, j) =>
  SceneryCell(GridPos(i, j)) 
  )
*/

  val enemyPath = game.enemyPath
  var grid = game.grid

  /*
    * logic how tower gets added to the arraybuffer might still need some extra thinking
    * might not work, think how a tower is added e.g. placeTower(Basic(gridPos(2,2)), gridPos(2,2))
    * also check if gridpos already populated
    * 
    * if it is, throw a new exception
  */

  def placeTower(tower: Tower, gridPos: GridPos): Boolean = 
    if ( grid(gridPos.x)(gridPos.y).canPlaceTower && !game.enemies.exists(_.position == gridPos) )then
      game.towers += tower
      true
    else 
      throw TowerException(
        s"Cannot place tower, check if the gridCell is already populated or whether it is a towerCell or not"
      )
      false
  end placeTower 

// think about is checking the grid too complex or should gridPos itself know if it has a tower
// is it possible in any case for val tower to be None?
  def destroyTower(gridPos: GridPos): Boolean = 
    if grid(gridPos.x)(gridPos.y).hasTower() then 
      game.towers = game.towers.filter( _.position != gridPos)
      true
    else 
      false
  end destroyTower
}
package game

import gridcells.{GridCell, SceneryCell}
import utils.{GridPos}
import utils.{Constants}
import towers.{Tower}
import game.{TowerDefenceGame}

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

  val enemyPath = game.constants.enemyPath
  var grid = game.constants.grid

// logic how tower gets added to the arraybuffer might still need some extra thinking
// might not work, think how a tower is added e.g. placeTower(Basic(gridPos(2,2)), gridPos(2,2))
  

  //also check if gridpos already populated
  // if it is, throw a new exception
  def placeTower(tower: Tower, gridPos: GridPos): Boolean = 
    if grid(gridPos.x)(gridPos.y).canPlaceTower then
      val t = tower
      game.towers += t
      true
    else 
      false
  end placeTower 

// think about is checking the grid too complex or should gridPos itself know if it has a tower
// is it possible in any case for val tower to be None?
  def destroyTower(gridPos: GridPos): Boolean = 
    if grid(gridPos.x)(gridPos.y).hasTower() then 
      val tower = game.towers.find(_.position == gridPos)
      game.towers -= tower.get
      true
    else 
      false
  end destroyTower
}
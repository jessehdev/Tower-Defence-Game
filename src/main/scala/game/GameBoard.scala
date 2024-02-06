package game

import gridcells.{GridCell, SceneryCell}
import utils.{GridPos}
import utils.{Constants}
import towers.{Tower}

class GameBoard (val width: Int, val height: Int) {
  val size = width * height
 
 /*  
  * initializes a grid consisting of scenery cells
  * use later on, when reading JSON files

  var grid: Array[Array[GridCell]] = Array.tabulate(width, height)( (i, j) =>
  SceneryCell(GridPos(i, j)) 
  )
*/

  //will be replaced with reading from JSON
  val constants = new Constants
  val enemyPath = constants.enemyPath
  var grid = constants.grid

  def placeTower(tower: Tower, gridPos: GridPos): Boolean = ???
    
  def destroyTower(gridPos: GridPos): Boolean = ???

}

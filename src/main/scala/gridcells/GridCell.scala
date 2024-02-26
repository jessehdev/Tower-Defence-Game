package gridcells
import utils.{GridPos}
import game.{TowerDefenceGame}
import towers.{Tower}
import scala.collection.mutable.ArrayBuffer
import scala.compiletime.ops.double


abstract class GridCell {
  val gridPos: GridPos

  //canPlaceTower wasn't in the original plan
  val canPlaceTower = false

/*
  * Two problems with below
  * 1) Should not create a new game instance, it should be sent as a parameter
  * 2) no need to use hasEnemy -- at leazt yet
  val game = new TowerDefenceGame
  var enemies = game.enemies
  var towers = game.towers

  def hasEnemy(): Boolean = 
    enemies.exists(_.position == this.gridPos) 
  end hasEnemy
*/

  def hasTower(towers: ArrayBuffer[Tower]): Boolean = 
  // THIS WAS THE ORIGINAL  towers.exists( _.position == this.gridPos )
    towers.exists(tower => tower.position.x == this.gridPos.x && tower.position.y == this.gridPos.y)
  end hasTower

  def isNeighbourOf(g: GridCell): Boolean =
    val dx = (this.gridPos.x - g.gridPos.x).abs
    val dy = (this.gridPos.y - g.gridPos.y).abs
    if (dx == 1 && dy <= 1) || (dy == 1 && dx <= 1) then
      true
    else
      false
  end isNeighbourOf
}
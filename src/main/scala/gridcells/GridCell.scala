package gridcells
import utils.{GridPos}
import game.{TowerDefenceGame}

abstract class GridCell {
  val gridPos: GridPos

  //canPlaceTower wasn't in the original plan
  val canPlaceTower = false

//will be replaced with reading JSON
  val game = new TowerDefenceGame
  var enemies = game.enemies
  var towers = game.towers

  def hasEnemy(): Boolean = 
    enemies.exists(_.position == this.gridPos) 
  end hasEnemy

  def hasTower(): Boolean = 
    towers.exists(_.position == this.gridPos)
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
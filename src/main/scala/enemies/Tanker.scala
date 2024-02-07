package enemies

import utils.GridPos

class Tanker extends Enemy {
  var health: Int = 1000
  val damage: Int = 300
  val attackSpeed: Int = 2
  var gridPos: GridPos = GridPos(0,2)
  var pathIndex: Int = 0
}

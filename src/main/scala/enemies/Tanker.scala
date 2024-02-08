package enemies

import utils.GridPos

class Tanker(var position: GridPos) extends Enemy {
  var health: Int = 1000
  val damage: Int = 300
  val attackSpeed: Int = 2
  var pathIndex: Int = 0
}

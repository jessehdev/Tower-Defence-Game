package enemies

import utils.GridPos

class Tanker extends Enemy {
  val health: Int = 1000
  val damage: Int = 300
  val attackSpeed: Int = 2
  val gridPos: GridPos = GridPos(0,0)
}

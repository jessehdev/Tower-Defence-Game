package enemies

import utils.{GridPos}

trait Enemy {
  val health: Int
  val damage: Int
  val attackSpeed: Int
  val gridPos: GridPos
 
  def getPos() = 
    this.gridPos
 
  def move() = ???
 
  def takeDamage() = ???
}
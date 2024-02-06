package enemies

import utils.{GridPos}

trait Enemy {
  //abstracts
  val health: Int
  val damage: Int
  val attackSpeed: Int
  val gridPos: GridPos
 
//concretes
  val enemyPath= ???

  def getPos() = 
    this.gridPos
 
  def move() = ???
 
  def takeDamage() = ???
}
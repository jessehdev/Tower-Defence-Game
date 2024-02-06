package enemies

import utils.{GridPos}
import utils.{Constants}

trait Enemy {
  //abstracts
  val health: Int
  val damage: Int
  val attackSpeed: Int
  var gridPos: GridPos
//added this, wasn't in the original plan
  var pathIndex: Int
//concretes
//constants should eventually be replaced with reading from JSON
  val constants = new Constants
  val enemyPath = constants.enemyPath

  def getPos() = 
    this.gridPos
 
  def move() = ???
 
  def takeDamage() = ???
}
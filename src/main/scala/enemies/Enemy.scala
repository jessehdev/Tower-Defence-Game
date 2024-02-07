package enemies

import utils.{GridPos}
import utils.{Constants}
import game.{TowerDefenceGame}

trait Enemy {
  //abstracts
  var health: Int
  val damage: Int
  val attackSpeed: Int
  var gridPos: GridPos
//added this, wasn't in the original plan
  var pathIndex: Int 
//concretes
//constants should eventually be replaced with reading from JSON
  val constants = new Constants
  val enemyPath = constants.enemyPath
  val game = new TowerDefenceGame
  val enemies = game.enemies
  
  def getPos() = 
    this.gridPos
  end getPos

  def move() = 
    pathIndex += 1
    this.gridPos = enemyPath(pathIndex).gridPos
  end move
  
  //added the construction parameter
  def takeDamage(amountOfDamage: Int) = 
    this.health = amountOfDamage
}
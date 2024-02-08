package enemies

import utils.{GridPos}
import utils.{Constants}
import game.{TowerDefenceGame}

trait Enemy {
  //abstracts
  var health: Int
  val damage: Int
  val attackSpeed: Int
  var position: GridPos
//added this, wasn't in the original plan
  var pathIndex: Int 

  def getPos() = 
    this.position
  end getPos

  // added construction parameter
  def move(game: TowerDefenceGame) = 
    pathIndex += 1
    this.position = game.enemyPath(pathIndex).gridPos
  end move
  
  //added the construction parameter
  def takeDamage(amountOfDamage: Int) = 
    this.health -= amountOfDamage
}
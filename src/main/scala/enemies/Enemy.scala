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

  override def toString(): String = 
    s"An enemy with $health amount of health at position $position"

  def getPos() = 
    this.position
  end getPos

  // added construction parameter
  def move(game: TowerDefenceGame) = 
    if pathIndex < game.enemyPath.length - 1 then
      pathIndex += 1
      this.position = game.enemyPath(pathIndex).gridPos
  end move
  
  //added the construction parameter
  def takeDamage(amountOfDamage: Int) = 
    this.health -= amountOfDamage
}
package enemies

import utils.{GridPos}
import utils.{Constants}
import game.{TowerDefenceGame, GameState}
import scala.util.Random

trait Enemy {
  //abstracts
  var health: Int
  val damage: Int
  val attackSpeed: Double
  var position: GridPos
//added this, wasn't in the original plan
  var pathIndex: Int 

  val rand = new Random
  
  val offsetX: Double = rand.nextDouble() * 25
  val offsetY: Double = rand.nextDouble() * 25

  override def toString(): String = 
    s"An enemy with $health amount of health at position $position"

  def getPos() = 
    this.position
  end getPos

  // added construction parameter
  def move(state: GameState, game: TowerDefenceGame) = 
    if game.tickCounter % (state.enemiesMoveModulo/attackSpeed) == 0 then
      if pathIndex < game.enemyPath.length - 1 then
        pathIndex += 1
        this.position = game.enemyPath(pathIndex).gridPos
  end move
  
  //added the construction parameter
  def takeDamage(amountOfDamage: Int) = 
    this.health -= amountOfDamage

  def getType: String
}
package enemies

import utils.{GridPos}
import utils.{Constants}
import game.{TowerDefenceGame, GameState}
import scala.util.Random

/* 
 * This code is the trait for an entity "enemy", which is trying
 * to navigate through a given path to the end goal, which
 * is the last GridCell of the path
 * The classes that extend this traitcan be found in the same 
 * directory as this, they are called "Fiend" and "Tanker" and
 * they both have different attributes (variable values)
 */

trait Enemy {
//abstract variables
  var health: Int
  val damage: Int
// attackSpeed is basically movement speed  
  val attackSpeed: Double
  var position: GridPos

// which index of the EnemyPath this enemy is currently in  
  var pathIndex: Int 

/* 
 * Offsets created randomly, depicts in the gui as 
 * slight variations in positions within a GridCell, so that
 * attacking enemies won't be on top of each other.
 * Value used in gui files
 */  
  val rand = new Random
  
  val offsetX: Double = rand.between(-20.0, 20.0)
  val offsetY: Double = rand.between(-20.0,20.0)

  override def toString(): String = 
    s"An enemy with $health amount of health at position $position"

  def getPos() = 
    this.position
  end getPos

/* 
 * Moves an enemy. Checks if enough time has passed and if has, then
 * goes to the next GridCell in the enemyPath
 * About the algorithm:
 * game.tickCounter is something that, at least during the writing of this,
 * ticks 60 times / second. state.EnemiesMoveModulo is an integer defined
 * in GameState. Attackspeed on the other hand is a double and depends on
 * the type of enemy. This algorithm checks the modulo calculation and
 * move an enemy in the enemyPath according to the condition
 */

  def move(state: GameState, game: TowerDefenceGame) = 
    if game.tickCounter % (state.enemiesMoveModulo/attackSpeed) == 0 then
      if pathIndex < game.enemyPath.length - 1 then
        pathIndex += 1
        this.position = game.enemyPath(pathIndex).gridPos
  end move
  
  def takeDamage(amountOfDamage: Int) = this.health -= amountOfDamage

  def getType: String
}
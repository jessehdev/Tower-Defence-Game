package game

import gridcells.{GridCell, SceneryCell}
import utils.{GridPos}
import utils.{Constants}
import towers.{Tower}
import enemies.{Enemy}
import game.{GameBoard}
import scala.collection.mutable.ArrayBuffer
import java.util.{Timer, TimerTask}

class TowerDefenceGame {
  val constants = new Constants
  val enemyPath = constants.enemyPath
  val enemies = constants.enemies
  var towers = constants.towers
  val waves = constants.waves

//variables used for gameTick
//look for documentation of java util timer, timertask and java lang runnable
  val timer = new Timer()
  var tickCounter = 0
  val task = new TimerTask {
    def run(): Unit = 
      tickCounter += 1
      gameTick() 
      if tickCounter % 1000 == 0 then
        println(s"Seconds passed: $tickCounter")
  }

  def gameTick() = 
    this.towers.foreach( _.shootEnemy() )
    this.enemies.foreach( _.move() )
    //todo: wave movement, check game state
  end gameTick
  
  def gameWon() = ???
  def gameOver() = ???
}

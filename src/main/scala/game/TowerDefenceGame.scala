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
  var enemies = constants.enemies
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
    //consider moving the below methods to gamestate or create now method in this class
    if (this.tickCounter % 800 == 0) then
        this.towers.foreach( _.shootEnemy(this.enemies) )
        this.enemies = this.enemies.filter( _.health >= 0 )

    if (this.tickCounter % 600 == 0) then
      this.enemies.foreach( _.move() )
    //todo: wave movement, check game state
  end gameTick
  
  val tickInterval = 1 // Tick interval in milliseconds
  timer.scheduleAtFixedRate(task, 0, tickInterval)

  def gameWon() = ???
  def gameOver() = ???
}

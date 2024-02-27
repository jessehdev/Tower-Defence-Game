package game

import gridcells.{GridCell, SceneryCell}
import utils.{GridPos, Constants}
import towers.{Tower}
import enemies.{Enemy}
import game.{GameBoard, GameState}
import scala.collection.mutable.ArrayBuffer
import java.util.{Timer, TimerTask}

class TowerDefenceGame {
  ///the below should be the only instance of constants in the entire project
  var constants = new Constants
  val enemyPath = constants.enemyPath
  //enemies originally was constants.enemies
  var enemies = new ArrayBuffer[Enemy]()
  //towers originally was constants.towers
  var towers = new ArrayBuffer[Tower]()
  val waves = constants.waves
  var grid = constants.grid

  var gameBoard = new GameBoard(this)

//variables used for gameTick
//look for documentation of java util timer, timertask and java lang runnable
  val timer = new Timer()
  var tickCounter = 0
  val task = new TimerTask {
    def run(): Unit = 
      tickCounter += 1
      gameTick() 
      if tickCounter % 60 == 0 then
        println(s"Seconds passed: $tickCounter")
  }
//contrary to initial plan, passin game as parameter to gamestate and having a reference in towerdefencegame
  var gameState = new GameState(this)

  // add a functionality: If any tower health is below 0 call gameboard.destroytower
  def gameTick() = 
    gameState.towersAttack()
    gameState.enemiesMove()
    gameState.startWave()
    //maybe check game state
  end gameTick
  
  val tickInterval = 16 // Tick interval in milliseconds
  timer.scheduleAtFixedRate(task, 0, tickInterval)

  def gameWon() = 
    if ( enemies.isEmpty && gameState.wavesLeft == 0 ) then 
      println("Game won!!!")
    // maybe update state to won, which ends the game
  end gameWon

  //last cell should be winning area cell
  def gameOver() = 
    enemies.exists(_.position == enemyPath.last)
}

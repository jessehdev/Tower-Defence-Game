package game

import gridcells.{GridCell, SceneryCell}
import utils.{GridPos, Constants}
import towers.{Tower}
import enemies.{Enemy}
import game.{GameBoard, GameState}
import scala.collection.mutable.ArrayBuffer
import java.util.{Timer, TimerTask}
import enemies._
import towers._

/*
 * TowerDefenceGame is the class that represents the game itself
 * Every single entity in the game is, in a way or another, accessible from here
 * The class has a couple methods including the game ticker algorithm and methods
 * checking if the game is lost or won
 */
class TowerDefenceGame {
  // The configurable constants for the game are loaded here. Includes the grid, waves of enemies, etc. 
  var constants = new Constants

  var enemies = ArrayBuffer[Enemy]()
  var towers = ArrayBuffer[Tower]()

  val enemyPath = constants.enemyPath
  val waves = constants.waves
  var grid = constants.grid

  var gameBoard = new GameBoard(this)
  var gameStateLost = false
  var gameStateWon = false

/*
 * The timer used for the gameTick -algorithm
 * Was introduced to me by ChatGPT, which provided a raw version for the timer
 * I made modification most notably to increase the tickCounter and the conditional statement 
 * The gameTick -algorithm is called every time the timer runs
 * Uses a java timer
 */
  var tickCounter = 0
  var currentTask = new TimerTask {
    def run(): Unit = 
      tickCounter += 1
      gameTick() 
      if tickCounter % 60 == 0 then
        println(s"Seconds passed: ${tickCounter / 60}")
  }

  var gameState = new GameState(this)

/*
 * Central method in the game. If the game isn't lost calls multiple other methods
 * which are reasonably explanatory by their names 
 * Without this gameTick -method basically nothing would happen in the game
 */
  def gameTick() = 
    if !gameStateLost then
      gameState.startWave()
      gameState.handleEnemySpawning()
      gameState.enemiesMove()
      gameState.towersAttack()
      gameBoard.updateAvailable()
      gameWon()
      gameOver()
  end gameTick

 // the variables below are used for the times above 

  val tickInterval = 1000 / 60 // Tick interval in milliseconds
  var currentTimer = new Timer
  def startTimer() = 
    val timer = currentTimer
    timer.scheduleAtFixedRate(currentTask, 0, tickInterval)

  def stopTimer() = 
    currentTimer.cancel()
    currentTimer = new Timer()
    currentTask = new TimerTask {
    def run(): Unit = 
      tickCounter += 1
      gameTick() 
      if tickCounter % 60 == 0 then
        println(s"Seconds passed: ${tickCounter / 60}")
  }

  def gameWon() = 
    if ( enemies.isEmpty && gameState.wavesLeft == 0 ) then 
      gameStateWon = true
  end gameWon

  //If a single enemy has reached the last GridCell of the enemyPath, the game is lost
  def gameOver() = 
    if enemies.exists( enemy => enemy.position.x == enemyPath.last.gridPos.x && enemy.position.y == enemyPath.last.gridPos.y ) then
      gameStateLost = true
}

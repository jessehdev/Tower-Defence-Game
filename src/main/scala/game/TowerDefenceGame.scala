package game

import gridcells.{GridCell, SceneryCell}
import utils.{GridPos, Constants, GameConfig}
import towers.{Tower}
import enemies.{Enemy}
import game.{GameBoard, GameState}
import scala.collection.mutable.{ArrayBuffer, Map}
import java.util.{Timer, TimerTask}
import enemies._
import towers._
import gridcells._
import java.io.File
import scalafx.animation.AnimationTimer

/*
 * TowerDefenceGame is the class that represents the game itself
 * Every single entity in the game is, in a way or another, accessible from here
 * The class has a couple methods including the game ticker algorithm and methods
 * checking if the game is lost or won
 */
class TowerDefenceGame(level: Int) {
  // The configurable constants for the game are loaded here. Includes the grid, waves of enemies and resoruces. 
  val config = GameConfig

  var enemies = ArrayBuffer[Enemy]()
  var towers = ArrayBuffer[Tower]()

  // the constants read from config levels
  var enemyPath: Array[PathCell] = config.generateEnemyPath(level).toArray
  var waves = config.generateWaves(level)
  var grid = config.generateGrid(level)
  val resources = config.generateInitialResources(level)
  var waveCount = config.getWaveCount(level)

  var gameBoard = new GameBoard(this)
  // flags for following the game state
  var gameStateLost = false
  var gameStateWon = false

  //Counts amount of levels in the game and stores it in a variable for the gui
  def countAmountOfLevels(path: String) = 
    val directory = new File(path)
      val files = directory.listFiles()
      files.count(_.isFile) 
  end countAmountOfLevels

  val numberOfLevels = countAmountOfLevels("src/main/levels")
  /*
   * A map for ochestrating towers shooting in the backend
   * and the animation in the frontend 
   */
  var towerShootingsMap: Map[Tower, ArrayBuffer[Enemy]] = Map.empty[Tower, ArrayBuffer[Enemy]]
  var towerHasShot: Boolean = false

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

  // method for stopping the timer for when the player wants to pause the game
  def stopTimer() = 
    currentTimer.cancel()
    currentTimer = new Timer()
    currentTask = new TimerTask {
      def run(): Unit = 
        tickCounter += 1
        gameTick() 
    }
    
  def stopGuiTimer(guiTimer: AnimationTimer) = guiTimer.stop()  

  def gameWon() = 
    if ( enemies.isEmpty && gameState.wavesLeft == 0 ) then 
      gameStateWon = true
  end gameWon

  //If a single enemy has reached the last GridCell of the enemyPath, the game is lost
  def gameOver() = 
    if enemies.exists( enemy => enemy.position.x == enemyPath.last.gridPos.x && enemy.position.y == enemyPath.last.gridPos.y ) then
      gameStateLost = true
}

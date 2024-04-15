package game
import game.{TowerDefenceGame}
import scala.collection.mutable.ArrayBuffer
import enemies.{Enemy}

/*
 * GameState handles enemie movement, attacking of towers, 
 * starting of waves and handling of enemy spawning within a wave  
 */
class GameState(game: TowerDefenceGame) {
  var enemiesKilled: Int = 0
  val amountOfWaves = game.waveCount
  var wavesLeft: Int = amountOfWaves

  var enemiesMoveModulo = 120
  var towersAttackModulo = 90
  var enemyQueue = ArrayBuffer[Enemy]()
  var spawnCounter = 0
  var spawnWait = 40
  val firstWaveModulo = 400
  val otherWaveModulo = 2000

  var player = new Player(game, this)

  def enemiesMove() =
    game.enemies.foreach( _.move(this, this.game) )
  end enemiesMove

 /*
  * Method for attacking of towers
  * If an enemy is killed, player earns 100 resources
  * Handles updating of current (alive) enemies within the game
  */ 
  def towersAttack() =
    if ( game.tickCounter % towersAttackModulo == 0 ) then
      game.towers.foreach( _.shootEnemy(game.enemies) )
      val killed = game.enemies.filterNot( _.health > 0).size
      if killed > 0 then
        player.earnResources(killed * 100)
      enemiesKilled += killed
      println(s"killed: $killed")
      game.enemies = game.enemies.filter( _.health > 0 )
      println(s"Resources: ${player.resources}, enemies killed: ${enemiesKilled}, waves left : ${wavesLeft}")
  end towersAttack

  /*
   * Method for starting a wave of enemies
   * First wave has different waiting time than other waves
   * Adds enemies to "enemyQueue" which is then used in handleEnemySpawning
   */
  def startWave() =
    var startWaveModulo = 600
    if wavesLeft == amountOfWaves then
      startWaveModulo = firstWaveModulo
    else
      startWaveModulo = otherWaveModulo
    if ( game.tickCounter % startWaveModulo == 0 && wavesLeft > 0 ) then
      val wave = game.waves(amountOfWaves - wavesLeft)
      enemyQueue = wave.enemies
      wavesLeft -= 1
  end startWave

  /*
   * Handles enemy spawning one by one. Time between spawnings depends on 
   * variable spawnWait, which can be configured
   */ 
  def handleEnemySpawning() =
    if enemyQueue.nonEmpty && spawnCounter >= spawnWait then
      game.enemies += enemyQueue.head
      enemyQueue = enemyQueue.tail
      spawnCounter = 0
    else 
      spawnCounter += 1
}
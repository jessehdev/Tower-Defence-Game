package game
import game.{TowerDefenceGame}
import scala.collection.mutable.ArrayBuffer
import enemies.{Enemy}

class GameState(game: TowerDefenceGame) {
  var enemiesKilled: Int = 0
  val amountOfWaves = game.constants.amountOfWaves
  var wavesLeft: Int = amountOfWaves

  var enemiesMoveModulo = 80
  var towersAttackModulo = 40
  var enemyQueue = ArrayBuffer[Enemy]()
  var spawnCounter = 0
  var spawnWait = 40
  val firstWaveModulo = 900
  val otherWaveModulo = 800
  // added gamestate and game as a construction parameters to player
  var player = new Player(game, this)

  def enemiesMove() =
    game.enemies.foreach( _.move(this, this.game) )
  end enemiesMove

  def towersAttack() =
    if ( game.tickCounter % towersAttackModulo == 0 ) then
      game.towers.foreach( _.shootEnemy(game.enemies) )
      val killed = game.enemies.filterNot( _.health >= 0).size
      enemiesKilled += killed
      println(s"killed: $killed")
      player.earnResources( 100 * killed )
      game.enemies = game.enemies.filter( _.health > 0 )
      println(s"Resources: ${player.resources}, enemies killed: ${enemiesKilled}, waves left : ${wavesLeft}")
  end towersAttack

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

  //handles enemy spawning every 0.5 seconds one by one
  def handleEnemySpawning() =
    if enemyQueue.nonEmpty && spawnCounter >= spawnWait then
      game.enemies += enemyQueue.head
      enemyQueue = enemyQueue.tail
      spawnCounter = 0
    else 
      spawnCounter += 1

  def updateState() = ???
}
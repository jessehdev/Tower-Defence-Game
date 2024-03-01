package game
import game.{TowerDefenceGame}

class GameState(game: TowerDefenceGame) {
  var enemiesKilled: Int = 0
  val amountOfWaves = game.constants.amountOfWaves
  var wavesLeft: Int = amountOfWaves

  var enemiesMoveModulo = 80
  var towersAttackModulo = 40
  // added gamestate and game as a construction parameters to player
  var player = new Player(game, this)

  def enemiesMove() =
     if ( game.tickCounter % enemiesMoveModulo == 0 ) then
       game.enemies.foreach( _.move(this.game) )
  end enemiesMove

  // uses the towers and enemies of a specific game instance
  def towersAttack() =
    if ( game.tickCounter % towersAttackModulo == 0 ) then
      game.towers.foreach( _.shootEnemy(game.enemies) )
      enemiesKilled += game.enemies.filterNot( _.health >= 0).size
      game.enemies = game.enemies.filter( _.health > 0 )
  end towersAttack

  //figure out if Thread.sleep works as intended, might block Main thread
  def startWave() =
    if ( game.tickCounter % 200 == 0 && wavesLeft > 0 ) then
      val wave = game.waves(amountOfWaves - wavesLeft)
      for i <- wave.enemies do
        //only adds tankers now, should add different types of enemies
        game.enemies += enemies.Tanker(game.enemyPath.head.gridPos)
      wavesLeft -= 1
  end startWave

  def updateState() = ???
}
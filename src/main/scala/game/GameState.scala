package game
import game.{TowerDefenceGame}

class GameState(game: TowerDefenceGame) {
  var enemiesKilled: Int = 0
 // var constants = game.constants => does this create a new constants and therefore doesnt update the original constants defined in towerdefencegme?
  val amountOfWaves = game.constants.amountOfWaves
  var wavesLeft: Int = amountOfWaves

  // added gamestate and game as a construction parameters to player
  var player = new Player(game, this)

  def enemiesMove() =
     if ( game.tickCounter % 600 == 0 ) then
       game.enemies.foreach( _.move() )
  end enemiesMove

  // uses the towers and enemies of a specific game instance
  def towersAttack() =
    if ( game.tickCounter % 800 == 0 ) then
      game.towers.foreach( _.shootEnemy(game.enemies) )
      game.enemies = game.enemies.filter( _.health >= 0 )
  end towersAttack

  //figure out if Thread.sleep works as intended, might block Main thread
  def startWave() =
    if ( game.tickCounter % 10000 == 0 && wavesLeft > 0 ) then
      val wave = game.waves(amountOfWaves - wavesLeft)
      for i <- wave.enemies do
        //only adds tankers now, should add different types of enemies
        game.enemies += enemies.Tanker(game.constants.initialPos)
        Thread.sleep(500)

      wavesLeft -= 1
  end startWave

  def updateState() = ???
}
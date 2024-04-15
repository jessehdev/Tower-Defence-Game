import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import towers._
import game._
import utils.GridPos
import scala.collection.mutable.ArrayBuffer
import enemies.Fiend

class TowerDefenceGameTest extends AnyFlatSpec with Matchers { 
    "Enemies" should "be empty when the game is first created" in {
        val game = TowerDefenceGame(1)
        game.enemies.size shouldBe 0
    }
    "Towers" should "be empty when the game is first created" in {
        val game = TowerDefenceGame(1)
        game.towers.size shouldBe 0
    }
    "Wavecount" should "be configured correctly" in {
        val game = TowerDefenceGame(2)
        game.waveCount shouldBe 5
    }
     "Resources" should "be configured correctly" in {
        val game = TowerDefenceGame(1)
        game.resources shouldBe 100
    }
    "CountAmountOfLevels" should "count the amount of levels correctly" in {
        val game = TowerDefenceGame(1)
        val amountOfLevels = game.countAmountOfLevels("src/main/levels")
        amountOfLevels shouldBe 3
    }
    "GameWon" should "change the game state when no enemies nor waves are left" in {
        val game = TowerDefenceGame(1)
        game.gameState.wavesLeft = 0
        game.enemies.clear()
        game.startTimer()
        game.gameTick()
        game.gameStateWon shouldBe true
    }
      "GameOver" should "change the game state when an enemy has reached the last path cell" in {
        val game = TowerDefenceGame(1)
        val lastCell = game.enemyPath.last
        val coords = (lastCell.gridPos.x, lastCell.gridPos.y)
        val fiend = Fiend(GridPos(coords._1, coords._2))
        game.enemies += fiend
        game.startTimer()
        Thread.sleep(100)
        game.gameTick()
        game.gameStateLost shouldBe true
      }
}  

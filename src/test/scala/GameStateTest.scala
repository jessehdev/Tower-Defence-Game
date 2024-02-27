import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import towers._
import enemies._
import utils.GridPos
import game._
import scala.collection.mutable.ArrayBuffer
import gridcells._

class GameStateTest extends AnyFlatSpec with Matchers { 
  "enemiesMove" should "move an enemy to correct GridCell" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val pos = GridPos(0,1)
    val tanker = new Tanker(pos)
    game.enemies += tanker
    game.tickCounter = 14
    state.enemiesMove()
    tanker.pathIndex shouldBe 1
    tanker.position.x shouldBe game.enemyPath(tanker.pathIndex).gridPos.x
    tanker.position.y shouldBe game.enemyPath(tanker.pathIndex).gridPos.y
  }
  it should "move multiple enemies correctly to GridCells on the enemyPath" in{
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val pos = GridPos(0,1)
    val pos2 = GridPos(2,1)
    val pos3 = GridPos(4,1)
    val tanker = new Tanker(pos)
    val tanker2 = new Tanker(pos2)
    val tanker3 = new Tanker(pos3)

    tanker2.pathIndex = 2
    tanker3.pathIndex = 4
    game.enemies += tanker
    game.enemies += tanker2
    game.enemies += tanker3
    game.tickCounter = 14
    state.enemiesMove()

    tanker.pathIndex shouldBe 1
    tanker.position.x shouldBe game.enemyPath(tanker.pathIndex).gridPos.x
    tanker.position.y shouldBe game.enemyPath(tanker.pathIndex).gridPos.y
    tanker2.pathIndex shouldBe 3
    tanker2.position.x shouldBe game.enemyPath(tanker2.pathIndex).gridPos.x
    tanker2.position.y shouldBe game.enemyPath(tanker2.pathIndex).gridPos.y
    tanker3.pathIndex shouldBe 5
    tanker3.position.x shouldBe game.enemyPath(tanker3.pathIndex).gridPos.x
    tanker3.position.y shouldBe game.enemyPath(tanker3.pathIndex).gridPos.y
  }
  it should "not move enemies, when game hasn't ticked enough yet" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val pos = game.enemyPath(0).gridPos
    val tanker = new Tanker(pos)
    game.enemies += tanker
    game.tickCounter = 8
    state.enemiesMove()
    tanker.pathIndex shouldBe 0
    tanker.position.x shouldBe game.enemyPath(tanker.pathIndex).gridPos.x //should be 0
    tanker.position.y shouldBe game.enemyPath(tanker.pathIndex).gridPos.y //should be 1
  }
  "towersAttack" should "attack enemies in range of towers" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val pos1 = GridPos(0,0)
    val pos2 = GridPos(20,20)
    val pos3 = GridPos(2,2)
    val pos4 = GridPos(18,18)
    val pos5 = GridPos(10,10)
    val tanker1 = new Tanker(pos1)
    val tanker2 = new Tanker(pos2)
    val comparisonTanker = new Tanker(pos5)
    val basic1 = new Basic(pos3)
    val splashTower = new SplashDamage(pos4)
    game.enemies += tanker1
    game.enemies += tanker2
    game.towers += basic1
    game.towers += splashTower
    game.tickCounter = 16
    state.towersAttack()
    tanker1.health shouldBe comparisonTanker.health - basic1.damage // should be 750
    tanker2.health shouldBe comparisonTanker.health - splashTower.damage // should be 900
  }
  it should "correctly eliminate an enemy when its health is below or equals 0" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val pos1 = GridPos(0,0)
    val pos2 = GridPos(2,2)
    val pos3 = GridPos(10,10)
    val pos4 = GridPos(11,10)
    val tanker = new Tanker(pos1)
    val tanker2 = new Tanker(pos3)
    val tanker3 = new Tanker(pos4)
    val basic = new Basic(pos2)
    tanker.health = basic.damage
    game.enemies += tanker
    game.enemies += tanker2
    game.enemies += tanker3
    game.enemies.length shouldBe 3
    game.towers += basic
    game.tickCounter = 16
    state.towersAttack()
    game.enemies.length shouldBe 2
  }
  it should "not deal damage to enemies when game hasn't ticked enough yet" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val pos1 = GridPos(0,0)
    val pos2 = GridPos(2,2)
    val pos3 = GridPos(11,10)
    val tanker = new Tanker(pos1)
    val comparisonTanker = new Tanker(pos3)
    val basic = new Basic(pos2)
    game.tickCounter = 15
    state.towersAttack()
    tanker.health shouldBe comparisonTanker.health
  }
}
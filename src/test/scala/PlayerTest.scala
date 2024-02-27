import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import towers._
import enemies._
import utils.GridPos
import game._
import scala.collection.mutable.ArrayBuffer
import gridcells._

class PlayerTest extends AnyFlatSpec with Matchers {  
  "Player" should "have the correct amount of resources after configuration" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val player = new Player(game, state)
    player.resources shouldBe 150
  }
  it should "increase the amount of resources correctly when calling earnResources" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val player = new Player(game, state)
    player.earnResources(300) 
    player.resources shouldBe 450
  }
  it should "not increase the amount of resources when calling earnResources with 0 as the parameter" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val player = new Player(game, state)
    player.earnResources(0)  
    player.resources shouldBe 150
  }
  "PurchaseTower" should "decrease the players resources correctly" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val player = new Player(game, state)
    player.earnResources(350)
    val pos = GridPos(0,2)
    val basic = new Basic(pos)
    player.purchaseTower(basic, pos)
    player.resources shouldBe 400
  }
  it should "throw a TowerException when player doesn't have enough resources" in  {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val player = new Player(game, state)
    val pos = GridPos(0,2)
    val splashTower = new SplashDamage(pos)
    assertThrows[TowerException] {
      player.purchaseTower(splashTower, pos) 
    }
  }
  "UpgradeTower" should "decrease the players resources correctly" in {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val player = new Player(game, state)
    player.earnResources(350)
    val pos = GridPos(0,2)
    val basic = new Basic(pos)
    player.upgradeTower(basic)
    player.resources shouldBe 300
  }
  it should "throw a TowerException when player doesn't have enough resources" in  {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    val player = new Player(game, state)
    val pos = GridPos(0,2)
    val basic = new Basic(pos)
    assertThrows[TowerException] {
      player.upgradeTower(basic) 
    }
  }
}  
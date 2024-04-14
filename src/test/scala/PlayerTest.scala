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
    val game = new TowerDefenceGame(1)
    val state = new GameState(game)
    val player = new Player(game, state)
    player.resources shouldBe game.resources
  }
  it should "increase the amount of resources correctly when calling earnResources" in {
    val game = new TowerDefenceGame(1)
    val state = new GameState(game)
    val player = new Player(game, state)
    player.earnResources(300) 
    player.resources shouldBe game.resources + 300
  }
  it should "not increase the amount of resources when calling earnResources with 0 as the parameter" in {
    val game = new TowerDefenceGame(1)
    val state = new GameState(game)
    val player = new Player(game, state)
    player.earnResources(0)  
    player.resources shouldBe game.resources
  }
  "PurchaseTower" should "decrease the players resources correctly" in {
    val game = new TowerDefenceGame(1)
    val state = new GameState(game)
    val player = new Player(game, state)
    player.earnResources(350)
    val pos = GridPos(0,2)
    val basic = new Basic(game, pos)
    player.purchaseTower(basic, pos)
    player.resources shouldBe game.resources + 350 - basic.cost
  }
  it should "return false when player doesn't have enough resources" in  {
    val game = new TowerDefenceGame(1)
    val state = new GameState(game)
    val player = new Player(game, state)
    player.resources = 20
    val pos = GridPos(0,2)
    val splashTower = new SplashDamage(game, pos)
    val a = player.purchaseTower(splashTower, pos)
    a shouldBe false
  }
  "UpgradeTower" should "decrease the players resources correctly" in {
    val game = new TowerDefenceGame(1)
    val state = new GameState(game)
    val player = new Player(game, state)
    player.earnResources(350)
    val pos = GridPos(0,2)
    val basic = new Basic(game, pos)
    game.towers += basic
    player.upgradeTower(basic.position)
    println(s"${player.resources}")
    val resourceDelta = 350 - basic.upgradeCost
    player.resources shouldBe game.resources + resourceDelta
  }
  it should "return false when player doesn't have enough resources" in  {
    val game = new TowerDefenceGame(1)
    val state = new GameState(game)
    val player = new Player(game, state)
    val pos = GridPos(0,2)
    val basic = new Basic(game, pos)
    game.towers += basic
    player.resources = 20
    val a = player.upgradeTower(basic.position)
    a shouldBe false
  }
}  
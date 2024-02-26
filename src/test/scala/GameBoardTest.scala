import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import towers._
import game._
import utils.GridPos
import scala.collection.mutable.ArrayBuffer

class GameBoardTest extends AnyFlatSpec with Matchers { 
  "DestroyTower" should "return true when destroying a tower and remove the towers from towers" in {
    val game = new TowerDefenceGame
    val board = new GameBoard(game)
    val gridPos = GridPos(3,3)
    val basic1 = new Basic(gridPos)
    game.towers += basic1
    val result = board.destroyTower(gridPos)
    result shouldBe true
    game.towers should not contain basic1
  }
  it should "return false when destroying a tower with wrong GridPos" in {
    val game = new TowerDefenceGame
    val board = new GameBoard(game)
    val gridPos = GridPos(3,3)
    val gridPos2 = GridPos(2,1)
    val basic1 = new Basic(gridPos)
    game.towers += basic1
    val result = board.destroyTower(gridPos2)
    result shouldBe false
  }
  it should "remove the correct amount of towers when called n times" in {
    val game = new TowerDefenceGame
    val board = new GameBoard(game)
    val gridPos = GridPos(3,3)
    val gridPos2 = GridPos(2,1)
    val gridPos3 = GridPos(3,5)
    val basic1 = new Basic(gridPos)
    val basic2 = new Basic(gridPos2)
    val basic3 = new Basic(gridPos3)
    game.towers += basic1 
    game.towers += basic2
    game.towers += basic3
    board.destroyTower(gridPos2)
    board.destroyTower(gridPos = gridPos3)
    game.towers.length shouldBe 1
  }
  "PlaceTower" should "return true when a tower is successfully placed on the grid" in {
    val game = new TowerDefenceGame
    val board = new GameBoard(game)
    val gridPos = GridPos(0,2) 
    val gridPos2 = GridPos(0,3)
    val tower = new Basic(gridPos)
    val result = board.placeTower(tower, gridPos)
    result shouldBe true
    game.towers should contain(tower)
  }
  it should "throw a TowerException when trying to place a tower on an occupied GridPos" in {
    val game = new TowerDefenceGame
    val board = new GameBoard(game)
    val gridPos = GridPos(0,2) 
    val tower = new Basic(gridPos)
    board.placeTower(tower, gridPos)
    val tower2 = new Basic(gridPos)
    assertThrows[TowerException] {
      board.placeTower(tower, gridPos) 
    }
}  
  it should "throw a TowerException when trying to place a tower on a wrong type of GridCell" in {
    val game = new TowerDefenceGame
    val board = new GameBoard(game)
    val gridPos = GridPos(0,5) 
    val tower = new Basic(gridPos)
    assertThrows[TowerException] {
      board.placeTower(tower, gridPos) 
    }  
  }
  "Towers" should "be an empty arraybuffer when initialized" in {
     val game = new TowerDefenceGame
     game.towers.length shouldBe 0
  }
   it should "be incremented when added towers" in {
    val game = new TowerDefenceGame
    val basic1 = new Basic(GridPos(3,3))
    val basic2 = new Basic(GridPos(4,5))
    game.towers += basic1
    game.towers += basic2
    game.towers.length shouldBe 2
  }
}
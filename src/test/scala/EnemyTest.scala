import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import towers._
import enemies._
import utils.GridPos
import game.TowerDefenceGame
import scala.collection.mutable.ArrayBuffer
import gridcells._

class EnemyTest extends AnyFlatSpec with Matchers {   
    "Tanker" should "have correct health when initialized" in {
        val tanker = new Tanker(GridPos(1,1))
        val compareTanker = new Tanker(GridPos(10,10))
        tanker.health shouldBe compareTanker.health
    }
    it should "have correct position when initialized" in {
        val tanker = new Tanker(GridPos(1,1))
        tanker.position.x shouldBe 1
        tanker.position.y shouldBe 1
    }
    it should "correctly reduce damage when shot at" in {
        val tanker = new Tanker(GridPos(1,1))
        val compareTanker = new Tanker(GridPos(10,10))
        tanker.takeDamage(700)
        tanker.health shouldBe compareTanker.health -700
    } 
    it should "not take any damage when shot at for 0 damage" in {
        val tanker = new Tanker(GridPos(1,1))
        val compareTanker = new Tanker(GridPos(10,10))
        tanker.takeDamage(0)
        tanker.health shouldBe compareTanker.health
    }
    it should "correctly move the defined path when move() is called" in {
        val tanker = new Tanker(GridPos(0,1))
        val game = new TowerDefenceGame
        tanker.move(game.gameState, game)
        tanker.pathIndex shouldBe 1
        tanker.position.x shouldBe game.enemyPath(1).gridPos.x
        tanker.position.y shouldBe game.enemyPath(1).gridPos.y
    }
    it should "have the correct position when calling getPos()" in {
        val tanker = new Tanker(GridPos(5,7))
        tanker.getPos().x shouldBe 5
        tanker.getPos().y shouldBe 7
    }
}    
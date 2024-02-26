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
        tanker.health shouldBe 1000
    }
    it should "have correct position when initialized" in {
        val tanker = new Tanker(GridPos(1,1))
        tanker.position.x shouldBe 1
        tanker.position.y shouldBe 1
    }
    it should "correctly reduce damage when shot at" in {
        val tanker = new Tanker(GridPos(1,1))
        tanker.takeDamage(700)
        tanker.health shouldBe 300
    } 
    it should "not take any damage when shot at for 0 damage" in {
        val tanker = new Tanker(GridPos(1,1))
        tanker.takeDamage(0)
        tanker.health shouldBe 1000
    }
    it should "correctly move the defined path when move() is called" in {
        val tanker = new Tanker(GridPos(0,1))
        val game = new TowerDefenceGame
        game.enemyPath = Array[GridCell](PathCell(GridPos(0,1)), PathCell(GridPos(1,1)), PathCell(GridPos(2,1)),
    PathCell(GridPos(3,1)), PathCell(GridPos(4,1)), WinningAreaCell(GridPos(5,1)))
        tanker.move(game)
        tanker.pathIndex shouldBe 1
        tanker.position.x shouldBe 1
        tanker.position.y shouldBe 1 
    }
    it should "have the correct position when calling getPos()" in {
        val tanker = new Tanker(GridPos(5,7))
        tanker.getPos().x shouldBe 5
        tanker.getPos().y shouldBe 7
    }
}    
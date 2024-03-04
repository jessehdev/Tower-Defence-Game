import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import towers._
import enemies._
import utils.GridPos
import scala.collection.mutable.ArrayBuffer

class TowerTest extends AnyFlatSpec with Matchers {
    "Basic" should "have the correct position after initialization" in {
        val basic = new Basic(GridPos(5,5))
        assert(basic.position.x === 5 && basic.position.y === 5)
    }
    it should "attack an enemy in range" in {  
        var enemyBuffer = ArrayBuffer[Enemy]()
        val basic = new Basic(GridPos(3,3))
        val tanker = new Tanker(GridPos(4,3))
        val compareTanker = new Tanker(GridPos(10,10))
        enemyBuffer += tanker
        basic.shootEnemy(enemyBuffer)
        assert(tanker.health < compareTanker.health)
    }
    it should "not attack a barely out of range enemy" in {
        var enemyBuffer = ArrayBuffer[Enemy]()
        val tanker = new Tanker(GridPos(0,0))
        val basic = new Basic(GridPos(3,4))
        val compareTanker = new Tanker(GridPos(10,10))
        enemyBuffer += tanker
        basic.shootEnemy(enemyBuffer)
        tanker.health shouldBe compareTanker.health
    }
    it should "upgrade correctly" in {
        var tower = new Basic(GridPos(1,1))
        tower.ugrade()
        tower.health shouldBe 1500
        tower.damage shouldBe 350
    }
    "Splashdamage" should "attack multiple enemies" in {
        var enemyBuffer = ArrayBuffer[Enemy]()
        val tanker1 = new Tanker(GridPos(2,2))
        val tanker2 = new Tanker(GridPos(2,3))
        val tanker3 = new Tanker(GridPos(3,2))
        val tanker4 = new Tanker(GridPos(4,3))
        val compareTanker = new Tanker(GridPos(20,20))
        enemyBuffer += tanker1
        enemyBuffer += tanker2
        enemyBuffer += tanker3
        enemyBuffer += tanker4
        var splashTower = new SplashDamage(GridPos(4,4))
        splashTower.shootEnemy(enemyBuffer)
        tanker1.health shouldBe compareTanker.health - splashTower.damage
        tanker2.health shouldBe compareTanker.health - splashTower.damage
        tanker3.health shouldBe compareTanker.health - splashTower.damage
        tanker4.health shouldBe compareTanker.health - splashTower.damage
    }
}    
end TowerTest
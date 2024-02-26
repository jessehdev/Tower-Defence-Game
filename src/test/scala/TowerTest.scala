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
        enemyBuffer += tanker
        basic.shootEnemy(enemyBuffer)
        assert(tanker.health < 1000)
    }
    it should "not attack a barely out of range enemy" in {
        var enemyBuffer = ArrayBuffer[Enemy]()
        val tanker = new Tanker(GridPos(0,0))
        val basic = new Basic(GridPos(3,4))
        enemyBuffer += tanker
        basic.shootEnemy(enemyBuffer)
        tanker.health shouldBe 1000
    }
    it should "upgrade correctly" in {
        var tower = new Basic(GridPos(1,1))
        tower.ugrade()
        tower.health shouldBe 1500
        tower.damage shouldBe 350
    }
}    
end TowerTest
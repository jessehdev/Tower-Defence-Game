import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import towers._
import enemies._
import utils.GridPos
import game.TowerDefenceGame
import scala.collection.mutable.ArrayBuffer
import gridcells._

class GridCellTest extends AnyFlatSpec with Matchers { 
  "HasTower" should "return true when the GridCell in question is occupied by a tower" in {
    var towerBuffer = ArrayBuffer[Tower]()
    val pos = GridPos(1,1)
    val cell = TowerCell(pos)
    val basic = new Basic(pos)
    towerBuffer += basic
    cell.hasTower(towerBuffer) shouldBe true
  }
  it should "return false when the GridCell in question is not occupied by a tower" in {
    var towerBuffer = ArrayBuffer[Tower]()
    val pos = GridPos(1,1)
    val pos2 = GridPos(3,3)
    val pos3 = GridPos(4,4)
    val cell = TowerCell(pos)
    val basic = new Basic(pos2)
    val splashTower = new SplashDamage(pos3) 
    towerBuffer += basic
    towerBuffer += splashTower
    cell.hasTower(towerBuffer) shouldBe false
  }  
  "isNeighbourOf" should "return true when given an adjacent GridCell as a parameter" in {
    val pos = GridPos(1,1)
    val pos2 = GridPos(2,1)
    val pos3 = GridPos(2,2)
    val cell = TowerCell(pos)
    val cell2 = TowerCell(pos2)
    val cell3 = TowerCell(pos3)
    cell.isNeighbourOf(cell2) shouldBe true
    cell.isNeighbourOf(cell3) shouldBe true
  }
  it should "return false when given a GridCell that is not adjacent to it as a parameter" in {
    val pos = GridPos(1,1)
    val pos2 = GridPos(3,2)
    val pos3 = GridPos(3,5)
    val cell = TowerCell(pos)
    val cell2 = TowerCell(pos2)
    val cell3 = TowerCell(pos3)
    cell.isNeighbourOf(cell2) shouldBe false
    cell.isNeighbourOf(cell3) shouldBe false
  }
}  
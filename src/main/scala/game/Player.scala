package game

import towers.{Tower, Basic}
import game.{GameState}
import utils.{GridPos}
import scala.collection.mutable.ArrayBuffer
import gui.{UpgradeView, PurchaseView}

case class TowerException(description: String)
    extends java.lang.Exception(description)

/*
 * Player class represent a player in the game.
 * A player has resources it can manage. Resources are gained by eliminating enemies.
 * Resources can be spend on buying or upgrading towers 
 */
class Player(game: TowerDefenceGame, gameState: GameState) {
  var constants = game.constants
  var resources = constants.initialResources

  // The two variables below are used in the gui, you can find corresponding file names to the variable names in gui/
  val upgradeView = UpgradeView(game)
  val purchaseView = PurchaseView(game, upgradeView)

  override def toString: String =
    s"Player with ($resources) amount of resources"

  def earnResources(r: Int) =
    resources += r
 
  /*
   * A player can purchase a tower if they have enough resources,
   * if the GridCell in question isn't occupied and is a TowerCell 
   */    
  def purchaseTower(tower: Tower, pos: GridPos): Boolean = 
    if resources >= tower.cost then
      game.gameBoard.placeTower(tower, pos)
      game.gameBoard.updateAvailable()
      resources -= tower.cost
      true
    else 
      purchaseView.showPurchasingError(tower)
      throw TowerException(
        s"Not enough resources to purchase tower, resources: ${resources} cost: ${tower.cost}")
      false
  end purchaseTower

    /*
    * A player can upgrade a tower if they have enough resources and the 
    * GridCell in question has a tower. 
    * The method checks for existing towers in the position given in the first line
    */    
  def upgradeTower(pos: GridPos): Boolean = 
    val arr = game.towers.filter( tower => tower.position.x == pos.x && tower.position.y == pos.y )
    val tower = arr.head
    if resources >= tower.upgradeCost then
      tower.ugrade()
      resources -= tower.upgradeCost
      true
    else
      upgradeView.showUpgradeError(pos)
      throw TowerException(
        s"Not enough resources to upgrade tower, resources: ${resources} cost: ${tower.cost}"
      )
      false
  end upgradeTower
}
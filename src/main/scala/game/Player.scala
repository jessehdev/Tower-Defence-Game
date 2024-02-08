package game

import towers.{Tower, Basic}
import game.{GameState}
import utils.{GridPos}

case class TowerException(description: String)
    extends java.lang.Exception(description)

class Player(game: TowerDefenceGame, gameState: GameState) {
 //using the same constants from game that are used in gamestate
  var constants = game.constants
  var resources = constants.initialResources

  override def toString: String =
    s"Player with ($resources) amount of resources"

  def earnResources(r: Int) =
    resources += r

// both purchaseTower and upgradeTower returned boolean in initial plan
  def purchaseTower(tower: Tower, gridPos: GridPos) = 
    if resources >= tower.cost then
      game.gameBoard.placeTower(tower, gridPos)
    else 
      throw TowerException(
        s"Not enough resources to purchase tower, resources: ${resources} cost: ${tower.cost}")

  def upgradeTower(tower: Tower) = 
    if resources >= tower.upgradeCost then
      tower.ugrade()
    else
      throw TowerException(
        s"Not enough resources to upgrade tower, resources: ${resources} cost: ${tower.cost}"
      )
}
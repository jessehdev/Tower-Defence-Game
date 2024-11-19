# Tower Defense Game built with Scala 3


The project is a tower defence game implemented in Scala. The central idea of the
fun and strategic game is that waves of enemies attack advancing a predetermined
path with an objective to reach a golden goal area.

The game board itself essentially consists of cells which are determined in a 2D
collection. Within a cell, depending on the type of the cell, an enemy (or multiple
enemies), a tower or neither can exist. The types of cells are path cells, where
enemies traverse, scenery cells, which are just for scenery and nothing happens in
them, tower cells, where towers can be placed and winning area cell(s) where the
game is won if an enemy reaches it.

The player defends by placing upgradable towers along the way of the enemy with
the objective to annihilate the enemy. If the towers are bought wrongly meaning (1)
the tower’s type hasn’t been selected (2) the tower’s position hasn’t been selected or
(3) the player doesn’t have enough resources, the player is informed with a
message. The same goes for upgrading a tower with the exception that its type isn’t
required to be selected. If an enemy reaches the goal area the game is instantly lost.
There are two types of enemies: fiends and tankers both with unique features.

Eliminating enemies gives the player resources, which the player can then use either
to buy new towers or to upgrade the existing ones. Once again the towers can be
placed in only selected cells, in the program code called “TowerCell”. There are two
types of towers: basic and splash damage, which again both have unique features.

In particular, the splash damage tower can shoot up to three enemies
simultaneously. The program keeps track of the number of eliminated enemies, the
amount of resources the player has and how many waves of enemies are left for the
level and shows this information for the player.

If enough waves of enemies are eliminated, the game is won. The game comes with
multiple levels which consist of different maps and different types of waves of
enemies. It is also possible to configure one’s own level with a JSON file.

## Features

- **Tower Defense Gameplay**: Defend against waves of enemies by strategically placing towers.
- **Two Enemy Types**:
  - **Fiends**: High speed, low health.
  - **Tankers**: Low speed, high health.
- **Two Tower Types**:
  - **Basic**: High single-target damage.
  - **Splash Damage**: Hits multiple enemies simultaneously.
- **Level Selection**: Freely choose levels from the GUI without completing previous ones.
- **Customizable Levels**: Create your own levels using JSON files.
- **Game Statistics**: Tracks resources, enemies killed, and waves remaining.
- **Pause/Play Functionality**: Take breaks anytime during gameplay.


## Installation

### Prerequisites
- **Java Development Kit (JDK)**: Version 11 or above.
- **Scala**: Version 3.0 or above.
- **Scala Build Tool (sbt)**.

### Setup
1. Clone the repository:
   ```bash
   git clone git@github.com:jessehdev/Tower-Defence-Game.git
   cd Tower-Defence-Game

2. Run the game:
   sbt run

3. The game will ask you which main class to use:
   select gui_elems.MainApp by typing "1" when prompted

## Testing
1. Run tests with the following command:
   sbt test

2. Check test coverage:
   sbt clean coverage test
   sbt coverageReport

## How to play

    Select a level from the top menu or play the default Level 1.
    Purchase towers from the Purchase Tower menu.
    Choose the tower type and its position.
    Place towers on the grid strategically.
    Start the game by pressing the Play button.
    Defend against enemies and prevent them from reaching the golden cell.
    Upgrade towers as needed to increase their power.

## Game Controls

- **Play/Pause**: Start or pause the game.
- **Level Selector**: Switch between levels at any time from the top status bar.
- **Purchase/Upgrade Towers**: Use the provided menus to manage towers.

### Technologies used
 1. Scala for game logic.
 2. ScalaFX for the graphical user interface.
 3. uPickle for JSON parsing.
 4. ScalaTest for unit testing.
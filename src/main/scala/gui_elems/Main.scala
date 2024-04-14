package gui_elems

import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene

/*
 * The game can be started from here 
 */
object MainApp extends JFXApp3 {
  var primaryStage = new PrimaryStage
  override def start() = 
    stage = new JFXApp3.PrimaryStage {
      title.value = "Tower Defence Game"
    // width = 800
    // height = 900
      scene = new Scene {
        root = GameView(1)
      }
    }
    primaryStage = stage
}
 
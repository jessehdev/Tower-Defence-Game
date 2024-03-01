import scalafx.application.JFXApp3
import scalafx.scene.Scene
import gui.GameView

object MainApp extends JFXApp3 {
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Tower Defence Game"
   //   width = 800
   //   height = 900
      scene = new Scene {
        root = GameView()
      }
    }
  }
}

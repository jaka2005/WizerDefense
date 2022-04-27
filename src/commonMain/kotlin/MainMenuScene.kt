import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.roundRect
import com.soywiz.korim.color.Colors

class MainMenuScene : Scene() {
    override suspend fun Container.sceneInit() {
        val startButton = roundRect(200, 50, 7, fill = Colors.LIGHTGREY) {
            position((screenWidth - width) / 2 , (screenHeight - height) / 2)
        }
    }
}
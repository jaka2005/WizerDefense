import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.container
import kotlin.properties.Delegates

class GameScene : Scene() {
    companion object {
        var enemyLayer: Container by Delegates.notNull()
        var towerLayer: Container by Delegates.notNull()
    }
    var backgroundLayer: Container by Delegates.notNull()

    override suspend fun Container.sceneInit() {
        backgroundLayer = container()
        enemyLayer = container()
        towerLayer = container()


    }
}

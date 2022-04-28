import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt

val screenWidth = 512
val screenHeight = 512

suspend fun main() = Korge(Korge.Config(module = ConfigModule))

object ConfigModule : Module() {
    override val size: SizeInt = SizeInt(screenWidth, screenHeight)

    override val mainScene = MainMenuScene::class
    override suspend fun AsyncInjector.configure() {
        mapPrototype { MainMenuScene() }
    }
}

import com.soywiz.korge.input.mouse
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point
import com.soywiz.korma.interpolation.Easing
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextInt

class MainMenuScene : Scene() {
    private var backgroundLayer: Container by Delegates.notNull()
    private var uiLayer: Container by Delegates.notNull()

    private var wizerRect: RoundRect by Delegates.notNull()
    private var bulletRect: RoundRect by Delegates.notNull()

    private var wizer1: Image by Delegates.notNull()
    private var bullet1: Image by Delegates.notNull()
    private var wizer2: Image by Delegates.notNull()
    private var bullet2: Image by Delegates.notNull()

    private var cursorBG: Image by Delegates.notNull()

    override suspend fun Container.sceneInit() {
        backgroundLayer = container()
        uiLayer = container()

        val wizerImage1 = resourcesVfs["wizer_pngs/wizer_explains.png"].readBitmap()
        val bulletImage1 = resourcesVfs["wizer_pngs/bullet_fucks.png"].readBitmap().flipX()
        val wizerImage2 = resourcesVfs["wizer_pngs/wizer_puzzled.png"].readBitmap()
        val bulletImage2 = resourcesVfs["wizer_pngs/bullet_yippee2.png"].readBitmap().flipX()

        val cursorImage = resourcesVfs["cursor.png"].readBitmap()

        wizer2 = backgroundLayer.image(wizerImage2) {
            scale = 175 / width
            position(-15, 120)
        }
        wizer1 = backgroundLayer.image(wizerImage1) {
            scale = 230 / width
            position(0, 95)
        }
        wizerRect = roundRect(105.0, wizer1.height * wizer1.scaleY - 50, .0) {
            alpha = .0
            x = 30.0
            y = wizer1.y + 50
        }

        bullet1 = backgroundLayer.image(bulletImage1) {
            scale = 200 / width
            position(300, 100)
        }
        bullet2 = backgroundLayer.image(bulletImage2) {
            visible = false
            scale = 200 / width
            position(350, 110)
        }
        bulletRect = roundRect(100.0, bullet1.height * bullet1.scaleY - 45, .0) {
            alpha = .0
            x = bullet1.x + 85.0
            y = bullet1.y + 45
        }

        cursorBG = backgroundLayer.image(cursorImage) {
            scale = 25 / width
            position(randomPoint())
        }

        val startButton = uiLayer.roundRect(200, 50, 7, fill = Colors.DARKGREY) {
            alpha = .5
            position((screenWidth - width) / 2 , (screenHeight - height) / 2 - 100)
            onClick { sceneContainer.changeTo<GameScene>() }
        }
        uiLayer.text("Начать игру").centerOn(startButton)
    }

    override suspend fun Container.sceneMain() {
        wizerRect.addUpdater {
            wizer1.visible = !mouse.isOver
            wizer2.visible = mouse.isOver
        }
        bulletRect.addUpdater {
            bullet1.visible = !mouse.isOver
            bullet2.visible = mouse.isOver
        }

        var cursorToPoint = randomPoint()
        while (true) {
            tween(
                cursorBG::x[cursorToPoint.x],
                cursorBG::y[cursorToPoint.y],
                easing = Easing.EASE_IN_OUT
            )
            cursorToPoint = randomPoint()
        }
    }

    override suspend fun sceneAfterDestroy() {
        println(this)
    }
}

fun randomPoint() =
    Point(Random.nextInt(0..screenWidth), Random.nextInt(0..screenHeight))

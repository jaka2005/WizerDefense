import com.soywiz.kds.getExtraTyped
import com.soywiz.kds.setExtra
import com.soywiz.korge.component.docking.keepChildrenSortedBy
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korma.geom.Point

class Tower(
    texture: Bitmap,
    val damage: Double,
    val cooldown: Double,
    val radius: Double,
    val bulletTexture: Bitmap
) : Sprite(texture)

class TowerPlace(
    position: Point,
    private var tower: Tower? = null,
    texture: Bitmap
) : Container() {

    init {
        image(texture).position(position)
    }

    fun isFree() = tower == null

    fun addTower(tower: Tower) {
        this.tower = tower.apply {
            addTo(GameScene.towerLayer)
            position(0.0, this@TowerPlace.height-height)
            setExtra("tower", y)
        }
        GameScene.towerLayer.keepChildrenSortedBy { it.getExtraTyped<Int>("tower") ?: 0 }
    }
    fun removeTower() {
        this.tower!!.removeFromParent()
        this.tower = null
    }
}
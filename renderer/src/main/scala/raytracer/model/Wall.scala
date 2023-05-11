package raytracer.model
import java.awt.Point
import org.scalajs.dom.CanvasRenderingContext2D
import org.scalajs.dom._
import scala.collection.immutable.Vector0
import raytracer.model.Player
import raytracer.model.Vector2D
import raytracer.model.Vector3D

case class Wall(start: Vector2D, end: Vector2D, color: scala.scalajs.js.Any) {
  lazy val normal: Vector2D = (end - start).perpendicular.normalize

  def center: Vector2D = (start + end) / 2
  def distanceTo(point: Vector2D): Double = {
    val v1 = end - start
    val v2 = point - start
    val distance = v1.perpDot(v2) / v1.length
    math.abs(distance)
  }

  def vertices(): List[Vector3D] = {
    val upVector = Vector3D(0, height / 2, 0)
    val downVector = Vector3D(0, -height / 2, 0)
    List(
        start.toVector3D() + upVector,
        start.toVector3D() + downVector,
        end.toVector3D() + downVector,
        end.toVector3D() + upVector
    )
  }
  def vertices2d(): List[Vector2D] = {
    List(start, end)
  }
  def height = 100

  // def render(
  //     ctx: CanvasRenderingContext2D,
  //     player: Player,
  //     planeDist: Double,
  //     canvasWidth: Int,
  //     canvasHeight: Int
  // ): Unit = {
  //   val playerPos = player.pos
  //   val playerDir = player.dir
  //   val wallHeight = height
  //   // Calculate the y position of the top and bottom of the wall on the screen
  //   val wallTop = (canvasHeight / 2) - (wallHeight / 2)
  //   val wallBottom = (canvasHeight / 2) + (wallHeight / 2)

  //   // Calculate the x positions of the left and right edges of the wall on the screen
  //   val wallLeft = {
  //     val relPos = start - playerPos
  //     val projX = relPos.dot(playerDir)
  //     (canvasWidth / 2) + (projX / planeDist * canvasWidth / 2)
  //   }
  //   val wallRight = {
  //     val relPos = end - playerPos
  //     val projX = relPos.dot(playerDir)
  //     (canvasWidth / 2) + (projX / planeDist * canvasWidth / 2)
  //   }

  //   // Draw a rectangle on the screen using the calculated positions and the wall's color
  //   ctx.fillStyle = color
  //   ctx.fillRect(wallLeft, wallTop, wallRight - wallLeft, wallHeight)

  //   // Wall.renderVerts(ctx, vertices().map(_.cut), color)

  //   // val textureWidth = 64
  //   // val textureHeight = 64
  //   // val textureColumn = (p1Distance * textureWidth).toInt % textureWidth

  //   // val wallSlice =
  //   // texture.slice(0, texture.length - (wallTop + wallBottom) / 2)

  //   // val textureImage = new Image
  //   // textureImage.src = wallSlice
  //   // textureImage.onload = { (e: Event) =>
  //   //   val texture = ctx.createPattern(
  //   //     textureImage.asInstanceOf[CanvasImageSource],
  //   //     "repeat"
  //   //   )
  //   //   ctx.fillStyle = texture
  //   //   ctx.fillRect(0, wallTop, screenWidth, wallBottom - wallTop)
  //   // }
  // }
}

object Wall {
  def renderVerts(
      ctx: CanvasRenderingContext2D,
      verts: List[Vector2D],
      fill: scalajs.js.Any
  ): Unit = {
    ctx.fillStyle = fill
    val head = verts.head
    ctx.beginPath()
    ctx.moveTo(head.x, head.y)
    for (vert <- verts.tail) ctx.lineTo(vert.x, vert.y)
    ctx.lineTo(head.x, head.y)
    ctx.closePath()
    ctx.fill()
  }
}

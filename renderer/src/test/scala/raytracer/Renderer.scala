package raytracer

import raytracer.model.Vector2D
import org.scalajs.dom._
import org.scalajs.dom
import raytracer.model.Wall
import raytracer.model.Vector3D

class Renderer(canvas: dom.html.Canvas, walls: Seq[Wall], playerPos: Vector2D, playerHeight: Double, fov: Double) {
  private val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  private val canvasWidth = canvas.width
  private val canvasHeight = canvas.height
  private val halfCanvasWidth = canvasWidth / 2
  private val halfCanvasHeight = canvasHeight / 2
  private val viewDist = canvasWidth
  private val scale = (canvasWidth + canvasHeight) / 1200.0

  def render(): Unit = {
    // TODO: rename to rotated
    val playerDir = Vector2D(1, 0).rotate(playerAngle)
    val leftBound = playerDir.rotate(-fov / 2)
    val rightBound = playerDir.rotate(fov / 2)

    for (x <- 0 until canvasWidth) {
        // TODO: implement lerp
      val rayDir = (leftBound.lerp(rightBound, x.toDouble / canvasWidth) * viewDist).toVector3D()
      val mapIntersection = findMapIntersection(playerPos.toVector3D(), rayDir)
      val dist = (mapIntersection - playerPos.toVector3D()).length

      val wallHeight = (canvasHeight / dist) * WALL_HEIGHT_SCALE

      val wall = findWall(mapIntersection, rayDir)
      if (wall.isDefined) {
        val wallDist = dist * math.cos(rayDir.angle - playerDir.angle)
        val wallHeight = (canvasHeight / wallDist) * WALL_HEIGHT_SCALE
        val wallTop = halfCanvasHeight - (wallHeight / 2) - (playerHeight * canvasHeight) / wallDist
        val wallBottom = halfCanvasHeight + (wallHeight / 2) - (playerHeight * canvasHeight) / wallDist

        ctx.fillStyle = wall.get.color.toString
        ctx.fillRect(x, wallTop, 1, wallBottom - wallTop)
      }
    }
  }

  private def findMapIntersection(origin: Vector3D, direction: Vector3D): Vector3D = {
    // implementation omitted for brevity
    ???
  }

  private def findWall(intersection: Vector3D, direction: Vector3D): Option[Wall] = {
    // implementation omitted for brevity
    ???
  }

  private val WALL_HEIGHT_SCALE = 1.0
  private val playerAngle = 0.0
}

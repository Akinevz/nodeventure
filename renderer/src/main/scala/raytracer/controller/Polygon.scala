package raytracer.controller

import org.scalajs.dom
import raytracer.model.Vector2D

case class Polygon(vertices: List[Vector2D], color: String) {
  def render(ctx: dom.CanvasRenderingContext2D): Unit = {
    ctx.fillStyle = color
    ctx.beginPath()
    ctx.moveTo(vertices.head.x, vertices.head.y)
    vertices.tail.foreach { v =>
      ctx.lineTo(v.x, v.y)
    }
    ctx.closePath()
    ctx.fill()
  }

//   def project(
//       playerPos: Vector2D,
//       playerDir: Vector2D,
//       canvasWidth: Double,
//       canvasHeight: Double
//   ): List[Vector2D] = {
//     vertices.map(v =>
//       Raytracer.projectPoint(v, playerPos, playerDir, canvasWidth, canvasHeight)
//     )
//   }
}

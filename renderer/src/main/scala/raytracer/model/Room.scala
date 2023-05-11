package raytracer.model

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html._
import org.scalajs.dom.raw.CanvasRenderingContext2D
import raytracer._

object Room {
  private val wallColor0 = "#555"
  private val wallColor1 = "#660"
  private val wallColor2 = "#605"
  private val wallColor3 = "#666"
  private val wallColor4 = "cyan"
  private val wallColor5 = "grey"

  def walls(origin: Vector2D, roomWidth: Double, roomDepth: Double) = {
    val Vector2D(x, y) = origin
    List(
        Wall(
            Vector2D(x + 0, y + 0),
            Vector2D(x + roomWidth, y + 0),
            wallColor1
        ), // top wall
        Wall(
            Vector2D(x + roomWidth, y + 0),
            Vector2D(x + roomWidth, y + roomDepth),
            wallColor2
        ), // right wall
        Wall(
            Vector2D(x + roomWidth, y + roomDepth),
            Vector2D(x + 0, y + roomDepth),
            wallColor1
        ), // bottom wall
        Wall(
            Vector2D(x + 0, y + roomDepth),
            Vector2D(x + 0, y + 0),
            wallColor2
        ) // left wall
    )

  }
}

case class Room(
    roomOrigin: Vector2D = Vector2D(0, 0),
    roomWidth: Double = 100.0,
    roomDepth: Double = 100.0,
    roomHeight: Double = 100.0
) {
  import Room.*
  lazy val walls = Room.walls(roomOrigin, roomWidth, roomDepth)
}

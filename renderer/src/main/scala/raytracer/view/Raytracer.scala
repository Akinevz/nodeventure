package raytracer.view
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import raytracer.model.Dungeon
import raytracer.model.Vector2D
import raytracer.model.Vector3D
import raytracer.model.Wall
import raytracer.Entry.Context
import raytracer.model.Player

object Raytracer {

  private val ceilingColor = "#ccc"
  private val floorColor = "#999"

  def clearScreen(canvas: Canvas): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    // Clear the canvas
    ctx.clearRect(0, 0, canvas.width, canvas.height)
  }
  def renderWall(
      ctx: Context,
      player: Player,
      wall: Wall,
      canvasWidth: Double,
      canvasHeight: Double
  ): Unit = {
    // Calculate the x positions of the start and end points of the wall

    // Calculate the height of the wall on the screen

    // Set the fill color to the wall's color

    // Loop over the columns of the wall and render them
    
  }

  def renderDungeon(
      canvas: Canvas,
      dungeon: Dungeon
  ): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    // val planeDist = 0.5 * canvas.width / math.tan(dungeon.player.FOV / 2)

    for (room <- dungeon.rooms)
      for (wall <- room.walls)
        renderWall(ctx, dungeon.player, wall, canvas.width, canvas.height)
        // renderWall(ctx, dungeon.player, planeDist, canvas.width, canvas.height)
  }

  def renderSky(canvas: Canvas): Unit = {
    lazy val ctx = canvas.getContext("2d")
    // Draw the floor
    // ctx.fillStyle = room.floor.color
    ctx.fillStyle = "#333"
    ctx.fillRect(0, canvas.height / 2, canvas.width, canvas.height / 2)
    // Draw the ceiling
    // ctx.fillStyle = room.ceiling.color
    ctx.fillStyle = "#eef"
    ctx.fillRect(0, 0, canvas.width, canvas.height / 2)
  }

  def projectPoint(
      point: Vector3D,
      playerPos: Vector2D,
      playerDir: Vector2D,
      planeDist: Double,
      canvasWidth: Int,
      canvasHeight: Int
  ): Vector2D = {
    val relPoint = point - playerPos.toVector3D()
    val x = relPoint.dot(playerDir)
    val y = relPoint.dot(playerDir.perpendicular)
    val canvasX = ((canvasWidth * x / planeDist) / 2 + canvasWidth / 2).toInt
    val canvasY = ((canvasHeight * y / planeDist) / 2 + canvasHeight / 2).toInt
    Vector2D(canvasX, canvasY)
  }
}

package raytracer.view

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html
import org.scalajs.dom.html._
import raytracer.model.Dungeon
import raytracer.model.Player
import raytracer.model.Room
import raytracer.Entry
import raytracer.Entry.Context

object TopDown {

  var once = false

  def once[A](some: => A): Unit = {
    if (!once) {
      some
      once = true
    }
  }

  def renderRoom(ctx: Context, room: Room): Unit = {
    for (wall <- room.walls) {
      val List(start, end) = wall.vertices2d()
      ctx.strokeStyle = wall.color
      ctx.beginPath()
      ctx.moveTo(start.x, start.y)
      ctx.lineTo(end.x, end.y)
      ctx.closePath()
      ctx.stroke()
    }
  }

  def renderPlayer(ctx: Context, player: Player): Unit = {
    val playerPos = player.pos
    ctx.beginPath()
    ctx.fillStyle = "red"
    ctx.arc(playerPos.x, playerPos.y, 10, 0, 2 * Math.PI)
    ctx.fill()
    ctx.closePath()
    ctx.beginPath()
    ctx.fillStyle = "cyan"
    val angle = player.dir.toAngle
    val fov = math.toRadians(player.FOV)
    val halfFov = fov/2
    console.log(angle)
    ctx.moveTo(playerPos.x, playerPos.y)
    ctx.arc(playerPos.x, playerPos.y, 20, angle - halfFov, angle + halfFov)
    ctx.fill()
    ctx.closePath()
  }

  def renderDungeon(canvas: Canvas, dungeon: Dungeon): Unit = {
    val ctx =
      canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    val Dungeon(rooms, player) = dungeon
    for(room <- rooms) renderRoom(ctx, room)
    renderPlayer(ctx, player)
  }
  def clear(canvas: Canvas): Unit = {
    val ctx =
      canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    val canvasWidth = canvas.width
    val canvasHeight = canvas.height
    ctx.fillStyle = "black"
    ctx.fillRect(0, 0, canvasWidth, canvasHeight)
  }
}

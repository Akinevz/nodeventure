package raytracer
import raytracer._
import raytracer.model._

import org.scalajs.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom._
import dom.document
import scala.scalajs.js.annotation.JSExportTopLevel
import raytracer.model.Dungeon
import raytracer.model.Room
import raytracer.view.Raytracer
import raytracer.view.TopDown

@JSExportTopLevel("Entry")
object Entry {

  var planeDist = 0.0

  type Context = CanvasRenderingContext2D
  def main(args: Array[String]): Unit = {
    dom.document.addEventListener("DOMContentLoaded", (e: dom.Event) => init())
  }

  def init() = {
    val canvas = dom.document.getElementById("render").asInstanceOf[Canvas]
    val topDown = dom.document.getElementById("topDown").asInstanceOf[Canvas]

    val room = Room(Vector2D(0, 0), 100, 100)
    val room2 = Room(Vector2D(500, 500), 200, 200)

    val playerHeight = 64
    val playerX: Double = room.roomWidth / 2
    val playerY: Double = room.roomHeight / 2

    val playerRotationVector: Vector2D = Vector2D(0, 1)

    val player = Player(
        Vector2D(playerX, playerY),
        playerRotationVector,
        playerHeight
    )

    val dungeon = Dungeon(List(room, room2), player)

    def render() = {
      Raytracer.clearScreen(canvas)
      Raytracer.renderSky(canvas)
      Raytracer.renderDungeon(canvas, dungeon)

      TopDown.clear(topDown)
      TopDown.renderDungeon(topDown, dungeon)
    }

    def renderLoop(): Unit = {
      render()
      dom.window.requestAnimationFrame((_: Double) =>
        dom.window.setTimeout(() => renderLoop(), 1000 / 60)
      )
    }
    // val slider = document.getElementById("slider").asInstanceOf[dom.html.Input]
    // slider.addEventListener(
    //     "input",
    //     (e: dom.Event) => {
    //       val value = slider.value
    //       planeDist = value.toDouble
    //     }
    // )
    window.addEventListener(
        "keydown",
        (event: dom.KeyboardEvent) => {
          // console.log("moving!")
          val keyCode = event.keyCode
          event.preventDefault()
          if (keyCode == KeyCode.Left) {
            player.dir =
              player.dir + (player.dir.rotate(-Math.PI / 2) * player.speed)
          } else if (keyCode == KeyCode.Right) {
            player.dir =
              player.dir - (player.dir.rotate(-Math.PI / 2) * player.speed)
          } else if (keyCode == KeyCode.Up) {
            player.pos = player.pos + (player.dir * player.rotSpeed)
          } else if (keyCode == KeyCode.Down) {
            player.pos = player.pos - (player.dir * player.rotSpeed)
          }
        }
    )
    renderLoop()
  }

}

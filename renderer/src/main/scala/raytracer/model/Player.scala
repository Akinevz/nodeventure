package raytracer.model
final class Player(var pos: Vector2D, var dir: Vector2D, val height: Double) {
  val speed = 0.1
  val rotSpeed = math.toRadians(15)
  val FOV = 67
}

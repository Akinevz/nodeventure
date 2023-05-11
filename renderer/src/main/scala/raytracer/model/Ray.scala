package raytracer.model
import raytracer._

case class Ray(origin: Vector2D, direction: Vector2D) {
  def apply(t: Double): Vector2D = origin + direction * t
}

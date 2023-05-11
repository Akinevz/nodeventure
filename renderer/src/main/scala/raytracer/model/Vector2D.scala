package raytracer.model
import java.awt.Point

case class Vector2D(x: Double, y: Double) {
  def toAngle: Double = math.atan2(y, x)
  def +(other: Vector2D): Vector2D = Vector2D(x + other.x, y + other.y)
  def -(other: Vector2D): Vector2D = Vector2D(x - other.x, y - other.y)
  def *(value: Double): Vector2D = Vector2D(x * value, y * value)
  def /(value: Double): Vector2D = Vector2D(x / value, y / value)
  def dot(other: Vector2D): Double = x * other.x + y * other.y
  def length: Double = math.sqrt(x * x + y * y)
  def normalize: Vector2D = this * (1 / length)
  def perpendicular: Vector2D = Vector2D(y, -x)

  def rotate(angle: Double): Vector2D = {
    val cos = math.cos(angle)
    val sin = math.sin(angle)
    Vector2D(x * cos - y * sin, x * sin + y * cos)
  }
  def perpDot(other: Vector2D): Double = x * other.y - y * other.x
  def perpDot(other: Vector3D): Double = x * other.y - y * other.x

  def distanceTo(v: Vector2D): Double =
    Math.sqrt(Math.pow(v.x - x, 2) + Math.pow(v.y - y, 2))

  def reflect(n: Vector2D): Vector2D = this - n * 2.0 * this.dot(n)

  def toPoint: Point = Point(x.toInt, y.toInt)

  def toVector3D(): Vector3D = this
}

object Vector2D {
  implicit def vectorsAreCompatible(v: Vector2D): Vector3D = {
    Vector3D(v.x, v.y, 0)
  }
}

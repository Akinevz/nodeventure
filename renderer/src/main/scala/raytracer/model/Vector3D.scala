package raytracer.model


class Vector3D(val x: Double, val y: Double, val z: Double) {
  def perpDot(other: Vector3D): Double = {
    x * other.y - y * other.x
  }
  def cut: Vector2D = Vector2D(x, y)
  def perpDot(other: Vector2D): Double = x * other.y - y * other.x
  def crossProduct(other: Vector3D): Vector3D = {
    new Vector3D(
      y * other.z - z * other.y,
      z * other.x - x * other.z,
      x * other.y - y * other.x
    )
  }
  def dot(other: Vector3D): Double = {
    x * other.x + y * other.y + z * other.z
  }
  def dotProduct(other: Vector3D): Double = {
    x * other.x + y * other.y + z * other.z
  }

  def magnitude: Double = {
    math.sqrt(x * x + y * y + z * z)
  }

  def normalize: Vector3D = {
    val mag = magnitude
    new Vector3D(x / mag, y / mag, z / mag)
  }

  def +(other: Vector3D): Vector3D = {
    new Vector3D(x + other.x, y + other.y, z + other.z)
  }

  def -(other: Vector3D): Vector3D = {
    new Vector3D(x - other.x, y - other.y, z - other.z)
  }

  def *(scalar: Double): Vector3D = {
    new Vector3D(x * scalar, y * scalar, z * scalar)
  }

  def /(scalar: Double): Vector3D = {
    new Vector3D(x / scalar, y / scalar, z / scalar)
  }
  def cross(other: Vector3D): Vector3D = Vector3D(
    y * other.z - z * other.y,
    z * other.x - x * other.z,
    x * other.y - y * other.x
  )
  def length: Double = math.sqrt(x * x + y * y + z * z)
  def rotateY(angle: Double): Vector3D = {
    val s = Math.sin(angle)
    val c = Math.cos(angle)
    Vector3D(x * c - z * s, y, x * s + z * c)
  }
}

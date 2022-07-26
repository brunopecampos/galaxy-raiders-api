@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

data class Point2D(val x: Double, val y: Double) {

  operator fun plus(p: Point2D): Point2D {
    val newPoint: Point2D = Point2D(this.x + p.x, this.y + p.y)
    return newPoint
  }

  operator fun plus(v: Vector2D): Point2D {
    val newPoint: Point2D = Point2D(this.x + v.dx, this.y + v.dy)
    return newPoint
  }

  override fun toString(): String {
    return "Point2D(x=$x, y=$y)"
  }

  fun toVector(): Vector2D {
    val newVector: Vector2D = Vector2D(this.x, this.y)
    return newVector
  }

  fun impactVector(p: Point2D): Vector2D {
    val newVector: Vector2D = Vector2D(Math.abs(this.x - p.x), Math.abs(this.y - p.y))
    return newVector
  }

  fun impactDirection(p: Point2D): Vector2D {
    return this.impactVector(p).unit 
  }

  fun contactVector(p: Point2D): Vector2D {
    return this.impactVector(p).normal
  }

  fun contactDirection(p: Point2D): Vector2D {
    return this.contactVector(p).unit
  }

  fun distance(p: Point2D): Double {
    return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y))
  }
}

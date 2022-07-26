@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = truncate(Math.sqrt(dx*dx + dy*dy))

  val radiant: Double
    get() = getRadiantVal(dx, dy)

  val degree: Double
    get() =  Math.toDegrees(radiant)

  val unit: Vector2D
    get() = Vector2D(dx / magnitude, dy / magnitude)

  val normal: Vector2D
    get() = Vector2D(unit.dy, -unit.dx)

  operator fun times(scalar: Double): Vector2D {
    val newVector: Vector2D = Vector2D(truncate(this.dx * scalar), truncate(this.dy * scalar))
    return newVector
  }

  operator fun div(scalar: Double): Vector2D {
    val newVector: Vector2D = Vector2D(truncate(this.dx / scalar), truncate(this.dy / scalar))
    return newVector
  }

  operator fun times(v: Vector2D): Double {
    val value: Double = truncate(truncate(this.dx * v.dx + this.dy * v.dy))
    return value
  }

  operator fun plus(v: Vector2D): Vector2D {
    val newVector: Vector2D = Vector2D(truncate(this.dx + v.dx), truncate(this.dy + v.dy))
    return newVector
  }

  operator fun plus(p: Point2D): Point2D {
    val newPoint: Point2D = Point2D(p.x + this.dx, p.y + this.dy)
    return newPoint 
  }

  operator fun unaryMinus(): Vector2D {
    val newVector: Vector2D = Vector2D(-this.dx, -this.dy)
    return newVector
  }

  operator fun minus(v: Vector2D): Vector2D {
    val newVector: Vector2D = Vector2D(this.dx - v.dx, this.dy - v.dy)
    return newVector
  }

  fun scalarProject(target: Vector2D): Double {
      return (this * target) / target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    val newVector: Vector2D = ((this * target) / truncate(target.magnitude * target.magnitude)) * target
    return newVector
  }
}

fun getRadiantVal(dx: Double, dy: Double): Double {
    val arccos : Double = Math.atan(Math.abs(dy) / Math.abs(dx))
    var result : Double = if (dx >= 0.0) arccos else Math.PI - arccos 
    result = if (dy >= 0.0) result else - result
    return result
  }

const val TRUNC: Double = 100.0
fun truncate(value: Double): Double {
  return Math.floor(value * TRUNC) / TRUNC
}

operator fun Double.times(v: Vector2D): Vector2D {
  val newVector : Vector2D = Vector2D(truncate(v.dx * this), truncate(v.dy * this))
  return newVector
}

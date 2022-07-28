package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DisplayName("Given an explosion")
class ExplosionTest {
  private val initPos = Point2D(1.0, 1.0)
  private val initVel = Vector2D(0.0, 0.0)

  private val explosion = Explosion(
    isTriggered = false,
    initialPosition = initPos,
    initialVelocity = initVel,
    radius = 2.0,
    mass = 0.0
  )

  @Test
  fun `it has a type Explosion`() {
    assertEquals("Explosion", explosion.type)
  }

  @Test
  fun `it has a symbol X `() {
    assertEquals('X', explosion.symbol)
  }

  @Test
  fun `it returns correct trigger property`() {
    assertEquals(false, explosion.isTriggered())
  }

  @Test
  fun `it returns can change trigger property`() {
    explosion.trigger()
    assertEquals(true, explosion.isTriggered())
  }
}

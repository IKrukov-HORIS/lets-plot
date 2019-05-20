package jetbrains.datalore.visualization.plot.gog.plot.event3

import jetbrains.datalore.base.geometry.DoubleRectangle
import jetbrains.datalore.visualization.plot.base.event3.GeomTargetLocator.LookupSpace
import jetbrains.datalore.visualization.plot.base.event3.GeomTargetLocator.LookupStrategy
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.createLocator
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.findTargets
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.horizontalPath
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.inside
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.outsideX
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.outsideXY
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.outsideY
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.pathTarget
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.point
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class HelperTest {

    @Test
    fun placementMethodsTest() {
        val r = DoubleRectangle(0.0, 100.0, 50.0, 50.0)
        run {
            val outsideX = outsideX(r)
            assertTrue(outsideX.x > r.right)
            assertTrue(outsideX.y > r.top && outsideX.y < r.bottom)
        }

        run {
            val outsideY = outsideY(r)
            assertTrue(outsideY.x > r.left && outsideY.x < r.right)
            assertTrue(outsideY.y > r.top)
        }

        run {
            val outsideXY = outsideXY(r)
            assertTrue(outsideXY.x > r.right)
            assertTrue(outsideXY.y > r.bottom)
        }

        run {
            val inside = inside(r)
            assertTrue(inside.x > r.left && inside.x < r.right)
            assertTrue(inside.y > r.top && inside.y < r.bottom)
        }
    }

    @Test
    fun pathIndexMapperTest() {
        val y = 100.0
        val path1 = horizontalPath(y, 0.0, 1.0, 2.0, 3.0)

        val target = pathTarget(path1)
        val locator = createLocator(LookupStrategy.NEAREST, LookupSpace.X, target)

        assertEquals(0, findTargets(locator, point(0.0, y))[0].hitIndex)
        assertEquals(1, findTargets(locator, point(1.0, y))[0].hitIndex)
        assertEquals(2, findTargets(locator, point(2.0, y))[0].hitIndex)
        assertEquals(3, findTargets(locator, point(3.0, y))[0].hitIndex)
    }

}
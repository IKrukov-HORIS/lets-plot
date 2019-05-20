package jetbrains.datalore.visualization.plot.gog.plot.event3

import jetbrains.datalore.base.geometry.DoubleRectangle
import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.visualization.plot.base.event3.GeomTargetLocator
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.assertEmpty
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.assertObjects
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.between
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.createLocator
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.inside
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.outsideX
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.point
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.polygon
import jetbrains.datalore.visualization.plot.gog.plot.event3.TestUtil.polygonTarget
import kotlin.test.Test

class MultiPolygonTest {

    private val polygonLocator: GeomTargetLocator
        get() = createLocator(GeomTargetLocator.LookupStrategy.HOVER, GeomTargetLocator.LookupSpace.XY, FIRST_TARGET)

    @Test
    fun pointInsideFirst_NotInHole_ShouldFindPolygon() {
        val locator = polygonLocator

        assertObjects(locator, between(FIRST_POLYGON_RECT, HOLE_RECT), FIRST_POLYGON_KEY)
    }

    @Test
    fun pointInsideFirst_InsideHole_ShouldFindNothing() {
        val locator = polygonLocator

        assertEmpty(locator, inside(HOLE_RECT))
    }

    @Test
    fun pointInsideSecond_ShouldFindPolygon() {
        val locator = polygonLocator

        assertObjects(locator, inside(SECOND_POLYGON_RECT), FIRST_POLYGON_KEY)
    }

    @Test
    fun pointRightFromSecond_ShouldFindNothing() {
        val locator = polygonLocator

        assertEmpty(locator, outsideX(SECOND_POLYGON_RECT))
    }

    companion object {

        /*
    200 *-------*
    150 |  *-*  |       *----*  150
        |  | |  |       |    |
     50 |  *-*  |       *----*  50
      0 *-------*
        0       200     400  500
  */

        private val HOLE_RECT = DoubleRectangle(50.0, 50.0, 100.0, 100.0)
        private val SECOND_POLYGON_RECT = DoubleRectangle(400.0, 150.0, 100.0, 100.0)
        private val FIRST_POLYGON_RECT = DoubleRectangle(0.0, 0.0, 200.0, 200.0)

        private val FIRST_POLYGON = TestUtil.multipolygon(
                polygonFromRect(HOLE_RECT),
                polygonFromRect(SECOND_POLYGON_RECT),
                polygonFromRect(FIRST_POLYGON_RECT)
        )

        private val FIRST_POLYGON_KEY = 1
        private val FIRST_TARGET = polygonTarget(FIRST_POLYGON_KEY, FIRST_POLYGON)

        private fun polygonFromRect(rect: DoubleRectangle): MutableList<DoubleVector> {

            return polygon(
                    point(rect.left, rect.top),
                    point(rect.left, rect.bottom),
                    point(rect.right, rect.bottom),
                    point(rect.right, rect.top))
        }
    }

}

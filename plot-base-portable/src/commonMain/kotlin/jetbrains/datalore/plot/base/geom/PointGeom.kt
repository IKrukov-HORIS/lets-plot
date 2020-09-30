/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plot.base.geom

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.values.Color
import jetbrains.datalore.plot.base.*
import jetbrains.datalore.plot.base.aes.AestheticsUtil
import jetbrains.datalore.plot.base.geom.util.GeomHelper
import jetbrains.datalore.plot.base.geom.util.HintColorUtil.fromColorValue
import jetbrains.datalore.plot.base.interact.GeomTargetCollector.TooltipParams
import jetbrains.datalore.plot.base.interact.GeomTargetCollector.TooltipParams.Companion.params
import jetbrains.datalore.plot.base.render.LegendKeyElementFactory
import jetbrains.datalore.plot.base.render.SvgRoot
import jetbrains.datalore.plot.base.render.point.NamedShape
import jetbrains.datalore.plot.base.render.point.PointShapeSvg
import jetbrains.datalore.plot.base.render.point.TinyPointShape
import jetbrains.datalore.plot.common.data.SeriesUtil
import jetbrains.datalore.vis.svg.slim.SvgSlimElements

open class PointGeom : GeomBase() {

    var animation: Any? = null
    var sizeUnit: String? = null
    private var sizeUnitScale: Double? = null

    override val legendKeyElementFactory: LegendKeyElementFactory
        get() = PointLegendKeyElementFactory()

    public override fun buildIntern(
        root: SvgRoot,
        aesthetics: Aesthetics,
        pos: PositionAdjustment,
        coord: CoordinateSystem,
        ctx: GeomContext
    ) {
        val helper = GeomHelper(pos, coord, ctx)
        val targetCollector = getGeomTargetCollector(ctx)

        val count = aesthetics.dataPointCount()
        val slimGroup = SvgSlimElements.g(count)
        for (i in 0 until count) {
            val p = aesthetics.dataPointAt(i)
            val x = p.x()
            val y = p.y()

            if (SeriesUtil.allFinite(x, y)) {
                val location = helper.toClient(DoubleVector(x!!, y!!), p)
                val shape = p.shape()!!
                val scaleFactor = getSizeUnitScaleFactor(ctx, p)

                targetCollector.addPoint(
                    i, location, scaleFactor * shape.size(p) / 2,
                    tooltipParams(p)
                )
                val o = PointShapeSvg.create(shape, location, p, scaleFactor)
                o.appendTo(slimGroup)
            }
        }
        root.add(wrap(slimGroup))
    }

    private fun getSizeUnitScaleFactor(ctx: GeomContext, p: DataPointAesthetics): Double {
        sizeUnitScale?.let { return sizeUnitScale!! }

        sizeUnit?.let {
            val pointSize = p.size() ?: return 1.0

            val aes = GeomHelper.getSizeUnitAes(sizeUnit!!)
            val unitRes = ctx.getUnitResolution(aes)

            val shape = p.shape()!!
            val size = shape.size(p)

            if (size == 0.0)
                return 1.0

            sizeUnitScale = unitRes * pointSize / size
            return sizeUnitScale!!
        }

        return 1.0
    }

    companion object {
        const val HANDLES_GROUPS = false

        fun tooltipParams(p: DataPointAesthetics): TooltipParams {
            var color = Color.TRANSPARENT
            if (p.shape() == TinyPointShape) {
                color = p.color()!!
            } else if (p.shape() is NamedShape) {
                val shape = p.shape() as NamedShape
                color = AestheticsUtil.fill(shape.isFilled, shape.isSolid, p)
            }

            return params().setColor(fromColorValue(color, p.alpha()!!))
        }
    }
}


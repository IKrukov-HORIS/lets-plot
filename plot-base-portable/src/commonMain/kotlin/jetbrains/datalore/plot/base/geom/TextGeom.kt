/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plot.base.geom

import jetbrains.datalore.base.gcommon.base.Strings
import jetbrains.datalore.base.numberFormat.NumberFormat
import jetbrains.datalore.plot.base.*
import jetbrains.datalore.plot.base.aes.AesScaling
import jetbrains.datalore.plot.base.geom.util.GeomHelper
import jetbrains.datalore.plot.base.render.LegendKeyElementFactory
import jetbrains.datalore.plot.base.render.SvgRoot
import jetbrains.datalore.plot.base.render.svg.TextLabel
import jetbrains.datalore.plot.common.data.SeriesUtil

class TextGeom : GeomBase() {
    var formatter: NumberFormat? = null
    var naValue = DEF_NA_VALUE
    var sizeUnit: String? = null
    private var sizeUnitScale: Double? = null

    override val legendKeyElementFactory: LegendKeyElementFactory
        get() = TextLegendKeyElementFactory()

    override fun buildIntern(
        root: SvgRoot,
        aesthetics: Aesthetics,
        pos: PositionAdjustment,
        coord: CoordinateSystem,
        ctx: GeomContext
    ) {
        val helper = GeomHelper(pos, coord, ctx)
        for (p in aesthetics.dataPoints()) {
            val x = p.x()
            val y = p.y()
            val text = toString(p.label())
            if (SeriesUtil.allFinite(x, y) && !Strings.isNullOrEmpty(text)) {
                val label = TextLabel(text)
                val scale = getScale(ctx, p)
                GeomHelper.decorate(label, p, scale)

                val loc = helper.toClient(x, y, p)
                label.moveTo(loc)
                root.add(label.rootGroup)
            }
        }
    }

    private fun approximateMaxTextWidth(p: DataPointAesthetics): Double {
        val testString = toString(TEST_VAL)
        val fontSize = AesScaling.textSize(p)

        return testString.length * fontSize * TEXT_WIDTH_NORM
    }

    private fun getScale(ctx: GeomContext, p: DataPointAesthetics): Double {
        sizeUnitScale?.let { return sizeUnitScale!! }
        sizeUnitScale = 1.0

        sizeUnit?.let {
            val aes = GeomHelper.getSizeUnitAes(sizeUnit!!)
            val unitRes = ctx.getUnitResolution(aes)
            val maxTextWidth = approximateMaxTextWidth(p)

            sizeUnitScale = unitRes / maxTextWidth
        }

        return sizeUnitScale!!
    }

    private fun toString(label: Any?): String {
        if (label == null) {
            return naValue
        }

        if (label is Double) {
            formatter?.let { return it.apply(label) }
        }

        return label.toString()
    }

    companion object {
        const val HANDLES_GROUPS = false
        const val DEF_NA_VALUE = "n/a"
        const val TEST_VAL = -9.99999
        const val TEXT_WIDTH_NORM = 0.6
    }
}

// How 'just' and 'angle' works together
// https://stackoverflow.com/questions/7263849/what-do-hjust-and-vjust-do-when-making-a-plot-using-ggplot
// ToDo: lineheight (aes)
// ToDo: nudge_x, nudge_y


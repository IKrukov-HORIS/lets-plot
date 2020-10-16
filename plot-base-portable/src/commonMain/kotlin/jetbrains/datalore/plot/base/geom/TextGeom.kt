/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plot.base.geom

import jetbrains.datalore.base.gcommon.base.Strings
import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.base.Aesthetics
import jetbrains.datalore.plot.base.CoordinateSystem
import jetbrains.datalore.plot.base.GeomContext
import jetbrains.datalore.plot.base.PositionAdjustment
import jetbrains.datalore.plot.base.aes.AesScaling
import jetbrains.datalore.plot.base.geom.util.GeomHelper
import jetbrains.datalore.plot.base.geom.util.HintColorUtil
import jetbrains.datalore.plot.base.interact.GeomTargetCollector
import jetbrains.datalore.plot.base.interact.TipLayoutHint
import jetbrains.datalore.plot.base.render.LegendKeyElementFactory
import jetbrains.datalore.plot.base.render.SvgRoot
import jetbrains.datalore.plot.base.render.svg.TextLabel
import jetbrains.datalore.base.stringFormat.StringFormat
import jetbrains.datalore.plot.common.data.SeriesUtil

class TextGeom : GeomBase() {
    var formatter: StringFormat? = null
    var naValue = DEF_NA_VALUE
    var sizeUnit: String? = null

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
        val targetCollector = getGeomTargetCollector(ctx)
        for (p in aesthetics.dataPoints()) {
            val x = p.x()
            val y = p.y()
            val text = toString(p.label())
            if (SeriesUtil.allFinite(x, y) && !Strings.isNullOrEmpty(text)) {
                val label = TextLabel(text)
                GeomHelper.decorate(label, p, getSizeUnitRatio(ctx, aesthetics))

                val loc = helper.toClient(x, y, p)
                label.moveTo(loc)
                root.add(label.rootGroup)

                targetCollector.addPoint(
                    p.index(),
                    loc,
                    AesScaling.textSize(p) / 2,
                    GeomTargetCollector.TooltipParams.params()
                        .setColor(HintColorUtil.fromColor(p))
                        .setStemLength(TipLayoutHint.StemLength.NONE)
                )
            }
        }
    }

    // TODO: Corrmatrix specific. Need universal implementation size_unit for various geoms
    private fun getSizeUnitRatio(ctx: GeomContext, aesthetics: Aesthetics): Double {
        fun estimateMaxTextWidth(fontSize: Double): Double {
            val testVal = -9.40
            val textWidthNorm = 0.6

            val testString = toString(testVal)
            return testString.length * fontSize * textWidthNorm
        }

        sizeUnit?.let { sizeUnitValue ->
            val fontSize = AesScaling.textSize(aesthetics.range(Aes.SIZE)?.upperEnd!!)
            val maxTextWidth = estimateMaxTextWidth(fontSize)

            if (maxTextWidth == 0.0) {
                return 1.0
            }

            val aes = GeomHelper.getSizeUnitAes(sizeUnitValue)
            val unitRes = ctx.getUnitResolution(aes)

            return unitRes / maxTextWidth
        }

        return 1.0
    }

    private fun toString(label: Any?): String {
        return when {
            label == null -> naValue
            formatter != null -> formatter!!.format(label)
            else -> label.toString()
        }
    }

    companion object {
        const val DEF_NA_VALUE = "n/a"
        const val HANDLES_GROUPS = false
    }
}

// How 'just' and 'angle' works together
// https://stackoverflow.com/questions/7263849/what-do-hjust-and-vjust-do-when-making-a-plot-using-ggplot
// ToDo: lineheight (aes)
// ToDo: nudge_x, nudge_y


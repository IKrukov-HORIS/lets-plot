package jetbrains.datalore.visualization.plot.gog.plot.layout

import jetbrains.datalore.base.gcommon.base.Preconditions.checkArgument
import jetbrains.datalore.base.gcommon.collect.ClosedRange
import jetbrains.datalore.base.geometry.DoubleRectangle
import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.visualization.plot.base.render.svg.TextLabel
import jetbrains.datalore.visualization.plot.gog.plot.guide.Orientation
import jetbrains.datalore.visualization.plot.gog.plot.layout.axis.GuideBreaks

class AxisLayoutInfo private constructor(b: Builder) {
    val axisBreaks: GuideBreaks?
    val axisLength: Double
    val orientation: Orientation?
    val axisDomain: ClosedRange<Double>?

    val tickLabelsBounds: DoubleRectangle?
    val tickLabelRotationAngle: Double
    val tickLabelHorizontalAnchor: TextLabel.HorizontalAnchor?    // optional
    val tickLabelVerticalAnchor: TextLabel.VerticalAnchor?        // optional
    val tickLabelAdditionalOffsets: List<DoubleVector>?           // optional
    val tickLabelSmallFont: Boolean
    internal val tickLabelsBoundsMax: DoubleRectangle?                     // debug

    init {
        checkArgument(b.myAxisBreaks != null)
        checkArgument(b.myOrientation != null)
        checkArgument(b.myTickLabelsBounds != null)
        checkArgument(b.myAxisDomain != null)

        this.axisBreaks = b.myAxisBreaks
        this.axisLength = b.myAxisLength
        this.orientation = b.myOrientation
        this.axisDomain = b.myAxisDomain

        this.tickLabelsBounds = b.myTickLabelsBounds
        this.tickLabelRotationAngle = b.myTickLabelRotationAngle
        this.tickLabelHorizontalAnchor = b.myLabelHorizontalAnchor
        this.tickLabelVerticalAnchor = b.myLabelVerticalAnchor
        this.tickLabelAdditionalOffsets = b.myLabelAdditionalOffsets
        this.tickLabelSmallFont = b.myTickLabelSmallFont
        this.tickLabelsBoundsMax = b.myMaxTickLabelsBounds
    }

    fun withAxisLength(axisLength: Double): Builder {
        //checkState(axisDomain != null);

        val b = Builder()
        b.myAxisBreaks = axisBreaks
        b.myAxisLength = axisLength

        b.myOrientation = this.orientation
        b.myAxisDomain = this.axisDomain

        b.myTickLabelsBounds = this.tickLabelsBounds
        b.myTickLabelRotationAngle = this.tickLabelRotationAngle
        b.myLabelHorizontalAnchor = this.tickLabelHorizontalAnchor
        b.myLabelVerticalAnchor = this.tickLabelVerticalAnchor
        b.myLabelAdditionalOffsets = this.tickLabelAdditionalOffsets
        b.myTickLabelSmallFont = this.tickLabelSmallFont
        b.myMaxTickLabelsBounds = this.tickLabelsBoundsMax
        return b
    }

    fun axisBounds(): DoubleRectangle {
        return tickLabelsBounds!!.union(DoubleRectangle(0.0, 0.0, 0.0, 0.0))
    }

    class Builder {
        var myAxisLength: Double = 0.toDouble()
        var myOrientation: Orientation? = null
        var myAxisDomain: ClosedRange<Double>? = null
        var myMaxTickLabelsBounds: DoubleRectangle? = null
        var myTickLabelSmallFont = false
        var myLabelAdditionalOffsets: List<DoubleVector>? = null
        var myLabelHorizontalAnchor: TextLabel.HorizontalAnchor? = null
        var myLabelVerticalAnchor: TextLabel.VerticalAnchor? = null
        var myTickLabelRotationAngle = 0.0
        var myTickLabelsBounds: DoubleRectangle? = null
        var myAxisBreaks: GuideBreaks? = null

        fun build(): AxisLayoutInfo {
            return AxisLayoutInfo(this)
        }

        fun axisLength(d: Double): Builder {
            myAxisLength = d
            return this
        }

        fun orientation(o: Orientation): Builder {
            myOrientation = o
            return this
        }

        fun axisDomain(r: ClosedRange<Double>): Builder {
            myAxisDomain = r
            return this
        }

        fun tickLabelsBoundsMax(r: DoubleRectangle?): Builder {
            myMaxTickLabelsBounds = r
            return this
        }

        fun tickLabelSmallFont(b: Boolean): Builder {
            myTickLabelSmallFont = b
            return this
        }

        fun tickLabelAdditionalOffsets(labelAdditionalOffsets: List<DoubleVector>?): Builder {
            myLabelAdditionalOffsets = labelAdditionalOffsets
            return this
        }

        fun tickLabelHorizontalAnchor(labelHorizontalAnchor: TextLabel.HorizontalAnchor?): Builder {
            myLabelHorizontalAnchor = labelHorizontalAnchor
            return this
        }

        fun tickLabelVerticalAnchor(labelVerticalAnchor: TextLabel.VerticalAnchor?): Builder {
            myLabelVerticalAnchor = labelVerticalAnchor
            return this
        }

        fun tickLabelRotationAngle(rotationAngle: Double): Builder {
            myTickLabelRotationAngle = rotationAngle
            return this
        }

        fun tickLabelsBounds(rectangle: DoubleRectangle?): Builder {
            myTickLabelsBounds = rectangle
            return this
        }

        fun axisBreaks(breaks: GuideBreaks?): Builder {
            myAxisBreaks = breaks
            return this
        }
    }
}

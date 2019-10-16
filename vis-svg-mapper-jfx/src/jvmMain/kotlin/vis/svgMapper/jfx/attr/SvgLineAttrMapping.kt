package jetbrains.datalore.vis.svgMapper.jfx.attr

import javafx.scene.shape.Line
import jetbrains.datalore.vis.svg.SvgLineElement
import jetbrains.datalore.vis.svgMapper.jfx.unScale

internal object SvgLineAttrMapping : SvgShapeMapping<Line>() {
    override fun setAttribute(target: Line, name: String, value: Any?) {
        when (name) {
            SvgLineElement.X1.name -> target.startX = unScale(asDouble(value))
            SvgLineElement.Y1.name -> target.startY = unScale(asDouble(value))
            SvgLineElement.X2.name -> target.endX = unScale(asDouble(value))
            SvgLineElement.Y2.name -> target.endY = unScale(asDouble(value))
            else -> super.setAttribute(target, name, value)
        }
    }
}
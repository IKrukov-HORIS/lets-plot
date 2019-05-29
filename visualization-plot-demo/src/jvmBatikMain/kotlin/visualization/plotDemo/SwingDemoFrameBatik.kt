package jetbrains.datalore.visualization.plotDemo

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.visualization.base.svg.SvgSvgElement
import jetbrains.datalore.visualization.base.svgToAwt.SvgAwtComponent
import jetbrains.datalore.visualization.base.svgToAwt.SvgAwtHelper
import java.awt.Dimension
import javax.swing.JComponent

class SwingDemoFrameBatik(title: String,
                          size: Dimension = FRAME_SIZE) : SwingDemoFrame(title, size) {

    override fun createSvgComponent(svgRoot: SvgSvgElement): JComponent {
        return SwingDemoFrameBatik.createSvgComponent(svgRoot)
    }

    companion object {
        fun showSvg(svgRoots: List<SvgSvgElement>, size: DoubleVector, title: String) {
            SwingDemoFrameBatik(title).showSvg(svgRoots, size)
        }

        fun createSvgComponent(svgRoot: SvgSvgElement): JComponent {
            return object : SvgAwtComponent(svgRoot) {
                override fun createMessageCallback(): SvgAwtHelper.MessageCallback {
                    return createDefaultMessageCallback()
                }
            }
        }
    }
}
package jetbrains.datalore.visualization.plot.base.render.geom

import jetbrains.datalore.visualization.plot.base.event3.GeomTargetCollector.TooltipParams
import jetbrains.datalore.visualization.plot.base.event3.GeomTargetCollector.TooltipParams.Companion.params
import jetbrains.datalore.visualization.plot.base.render.Aes
import jetbrains.datalore.visualization.plot.base.render.DataPointAesthetics
import jetbrains.datalore.visualization.plot.base.render.geom.util.HintColorUtil.fromColor

class DensityGeom : AreaGeom() {

    override fun setupTooltipParams(aes: DataPointAesthetics): TooltipParams {
        return params().setColor(fromColor(aes))
    }

    companion object {
        val RENDERS: List<Aes<*>> = AreaGeom.RENDERS

        val HANDLES_GROUPS = AreaGeom.HANDLES_GROUPS
    }


}

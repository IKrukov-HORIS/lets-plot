/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.plotConfig

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.plot.builder.presentation.Style.JFX_PLOT_STYLESHEET
import jetbrains.datalore.plotDemo.model.plotConfig.CorrelationStat
import jetbrains.datalore.vis.demoUtils.SceneMapperDemoFactory

object CorrelationStatJfx {
    @JvmStatic
    fun main(args: Array<String>) {

        with(CorrelationStat()) {
            @Suppress("UNCHECKED_CAST")
            val plotSpecList = plotSpecList() as List<MutableMap<String, Any>>
            @Suppress("SpellCheckingInspection")
            PlotConfigDemoUtil.show(
                "Correlation stat demo",
                plotSpecList,
                SceneMapperDemoFactory(JFX_PLOT_STYLESHEET),
                DoubleVector(600.0, 300.0)
            )
        }
    }
}
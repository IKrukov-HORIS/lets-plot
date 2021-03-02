/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.plotConfig

import jetbrains.datalore.plotDemo.model.plotConfig.Raster
import jetbrains.datalore.vis.demoUtils.PlotSpecsViewerDemoWindowBatik

object RasterBatik {
    @JvmStatic
    fun main(args: Array<String>) {
        with(Raster()) {
            PlotSpecsViewerDemoWindowBatik.show(
                "geom_raster",
                plotSpecList()
            )
        }
    }
}

package jetbrains.datalore.visualization.plot.base.event3

import jetbrains.datalore.visualization.plot.base.event.MappedDataAccess
import jetbrains.datalore.visualization.plot.base.render.Aes

// `open` for Mockito tests
open class ContextualMapping(
        val tooltipAes: List<Aes<*>>,
        val axisAes: List<Aes<*>>,
        val dataAccess: MappedDataAccess
)
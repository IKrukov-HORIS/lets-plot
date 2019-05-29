package jetbrains.datalore.visualization.plotDemo.model.plotContainer

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.observable.property.ReadableProperty
import jetbrains.datalore.base.values.Color
import jetbrains.datalore.visualization.plot.base.Aes
import jetbrains.datalore.visualization.plot.base.Scale
import jetbrains.datalore.visualization.plot.base.data.DataFrameUtil
import jetbrains.datalore.visualization.plot.base.interact.GeomTargetLocator
import jetbrains.datalore.visualization.plot.base.scale.Scales
import jetbrains.datalore.visualization.plot.base.stat.Stats
import jetbrains.datalore.visualization.plot.builder.PlotContainer
import jetbrains.datalore.visualization.plot.builder.VarBinding
import jetbrains.datalore.visualization.plot.builder.assemble.GeomLayerBuilder
import jetbrains.datalore.visualization.plot.builder.assemble.PlotAssembler
import jetbrains.datalore.visualization.plot.builder.assemble.PosProvider
import jetbrains.datalore.visualization.plot.builder.assemble.geom.GeomProvider
import jetbrains.datalore.visualization.plot.builder.coord.CoordProviders
import jetbrains.datalore.visualization.plot.builder.interact.GeomInteractionBuilder
import jetbrains.datalore.visualization.plot.builder.theme.DefaultTheme

class BarPlotResizeDemo private constructor(
        private val sclData: SinCosLineData,
        private val xScale: Scale<*>) {

    fun createPlot(plotSize: ReadableProperty<DoubleVector>): PlotContainer {
        val varX = sclData.varX
        val varY = sclData.varY
        val varCat = sclData.varCat
        val data = sclData.dataFrame

        val categories = ArrayList(DataFrameUtil.distinctValues(data, varCat))
        val colors = listOf(Color.RED, Color.BLUE, Color.CYAN)
        val fillScale = Scales.pureDiscrete("C", categories, colors, Color.GRAY)


        val layerBuilder = GeomLayerBuilder.demoAndTest()
                .stat(Stats.IDENTITY)
                .geom(GeomProvider.bar())
                .pos(PosProvider.dodge())
                .groupingVar(varCat)
                .addBinding(VarBinding(varX, Aes.X, xScale))
                .addBinding(VarBinding(varY, Aes.Y, Scales.continuousDomain("sin, cos, line", Aes.Y)))
                .addBinding(VarBinding(varCat, Aes.FILL, fillScale))
                .addConstantAes(Aes.WIDTH, 0.9)

        // Add bar plot interactions
        val geomInteraction = GeomInteractionBuilder(listOf(Aes.X, Aes.Y, Aes.FILL))
                .univariateFunction(GeomTargetLocator.LookupStrategy.NEAREST)
                .build()
        val layer = layerBuilder
                .locatorLookupSpec(geomInteraction.createLookupSpec())
                .contextualMappingProvider(geomInteraction)
                .build(data)


        //Theme t = new DefaultTheme() {
        //  @Override
        //  public AxisTheme axisX() {
        //    return new DefaultAxisTheme() {
        //      @Override
        //      public boolean showTickLabels() {
        //        return false;
        //      }
        //    };
        //  }
        //};
        val assembler = PlotAssembler.singleTile(listOf(layer), CoordProviders.cartesian(), DefaultTheme())
//        assembler.disableInteractions()
        return PlotContainer(assembler.createPlot(), plotSize)
    }

    companion object {

        fun continuousX(): BarPlotResizeDemo {
            return BarPlotResizeDemo(
                    SinCosLineData({ v -> v.toDouble() }, 6),
                    Scales.continuousDomain(" ", Aes.X))
        }

        fun discreteX(): BarPlotResizeDemo {
            val sclData = SinCosLineData({ v -> "Group label " + (v + 1) }, 6)
            return BarPlotResizeDemo(
                    sclData,
                    Scales.discreteDomain<String>("", sclData.distinctXValues()))
        }
    }
}

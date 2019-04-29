package jetbrains.datalore.visualization.plot.gog.core.data.sampling

import jetbrains.datalore.base.gcommon.base.Preconditions.checkArgument
import jetbrains.datalore.visualization.plot.gog.core.data.DataFrame
import jetbrains.datalore.visualization.plot.gog.core.data.GroupAwareSampling
import jetbrains.datalore.visualization.plot.gog.plot.data.GroupUtil
import java.util.stream.Collectors.toList
import java.util.stream.IntStream
import kotlin.random.Random

internal class RandomStratifiedSampling internal constructor(sampleSize: Int, private val mySeed: Long?, private val myMinSubsampleSize: Int?) : SamplingBase(sampleSize), GroupAwareSampling {

    override val expressionText: String
        get() = "sampling_" + ALIAS + "(" +
                "n=" + sampleSize +
                (if (mySeed != null) ", seed=$mySeed" else "") +
                (if (myMinSubsampleSize != null) ", min_subsample=$myMinSubsampleSize" else "") +
                ")"

    override fun isApplicable(population: DataFrame, groupMapper: (Int) -> Int): Boolean {
        return population.rowCount() > sampleSize
    }

    override fun apply(population: DataFrame, groupMapper: (Int) -> Int): DataFrame {
        checkArgument(isApplicable(population, groupMapper))
        val indicesByGroup = GroupUtil.indicesByGroup(population.rowCount(), groupMapper)

        var minSubSampleSize = myMinSubsampleSize ?: DEF_MIN_SUBSAMPLE_SIZE
        minSubSampleSize = Math.max(0, minSubSampleSize)
        val popSize = population.rowCount()

        val pickIndices = ArrayList<Int>()
        val rand = mySeed?.let { Random(it) } ?: Random.Default
        for (group in indicesByGroup.keys) {
            val groupIndices = indicesByGroup[group]!!
            val stratumSize = groupIndices.size
            // proportionate allocation
            val ratio = stratumSize.toDouble() / popSize

            var stratumSampleSize = Math.round(sampleSize * ratio).toInt()
            stratumSampleSize = Math.max(stratumSampleSize, minSubSampleSize)

            if (stratumSampleSize >= stratumSize) {
                pickIndices.addAll(groupIndices)
            } else {

                val sampleGroupIndices = SamplingUtil.sampleWithoutReplacement<List<Int>>(stratumSize, stratumSampleSize, rand,
                        { indexSet ->
                            IntStream.range(0, groupIndices.size).boxed()
                                    .filter({ indexSet.contains(it) })
                                    .collect(toList())
                        },
                        { indexSet ->
                            IntStream.range(0, groupIndices.size).boxed()
                                    .filter { i -> !indexSet.contains(i) }
                                    .collect(toList())
                        })

                for (i in sampleGroupIndices) {
                    pickIndices.add(groupIndices[i])
                }
            }
        }

        return population.selectIndices(pickIndices)
    }

    companion object {
        internal val ALIAS = "random_stratified"
        private val DEF_MIN_SUBSAMPLE_SIZE = 2 // min needed to draw a line
    }
}

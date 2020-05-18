/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plot.base.stat

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.base.DataFrame
import jetbrains.datalore.plot.base.StatContext
import jetbrains.datalore.plot.base.stat.CorrelationUtil.correlationMatrix
import jetbrains.datalore.plot.base.stat.math3.correlationPearson
import kotlin.math.abs


class CorrelationStat : BaseStat(DEF_MAPPING) {
    var correlationMethod = DEF_CORELATION_METHOD

    override fun apply(data: DataFrame, statCtx: StatContext, messageConsumer: (s: String) -> Unit): DataFrame {
        if (correlationMethod != Method.PEARSON)
            throw IllegalArgumentException(
                "Unsupported correlation method: $correlationMethod (only pearson is currently available)"
            )

        val cm = correlationMatrix(data, ::correlationPearson)
        val abs: List<Double> = cm.getNumeric(Stats.CORR).map { abs(it!!) }

        return cm.builder().putNumeric(Stats.ABS_VAL, abs).build()
    }

    override fun consumes(): List<Aes<*>> {
        return listOf( Aes.X, Aes.Y )
    }

    enum class Method {
        PEARSON,
        SPEARMAN,
        KENDALL
    }

    companion object {

        private val DEF_MAPPING: Map<Aes<*>, DataFrame.Variable> = mapOf(
            Aes.X to Stats.VAR1,
            Aes.Y to Stats.VAR2,
            Aes.COLOR to Stats.CORR,
            Aes.SIZE to Stats.ABS_VAL
        )

        private val DEF_CORELATION_METHOD = Method.PEARSON
    }
}
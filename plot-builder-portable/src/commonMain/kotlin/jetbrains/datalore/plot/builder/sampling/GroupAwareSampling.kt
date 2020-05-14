/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plot.builder.sampling

import jetbrains.datalore.plot.base.DataFrame

interface GroupAwareSampling : Sampling {
    fun isApplicable(population: DataFrame, groupMapper: (Int) -> Int): Boolean

    fun apply(population: DataFrame, groupMapper: (Int) -> Int): DataFrame
}
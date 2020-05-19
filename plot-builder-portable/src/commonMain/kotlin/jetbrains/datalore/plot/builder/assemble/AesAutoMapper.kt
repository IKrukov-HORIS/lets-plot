/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plot.builder.assemble

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.base.DataFrame

interface AesAutoMapper {
    fun createMapping(data: DataFrame, statMapping: Map<Aes<*>, DataFrame.Variable>? ): Map<Aes<*>, DataFrame.Variable>
}

/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plot.builder.theme

import jetbrains.datalore.base.values.Color

interface LegendTheme {
    fun keySize(): Double

    /**
     * extra space added around legend (px, no support for ggplot 'units')
     */
    fun margin(): Double

    /**
     * space around legend content (px)
     * this is not part of ggplot specs
     */
    fun padding(): Double

    fun position(): jetbrains.datalore.plot.builder.guide.LegendPosition

    fun justification(): jetbrains.datalore.plot.builder.guide.LegendJustification

    fun direction(): jetbrains.datalore.plot.builder.guide.LegendDirection

    fun backgroundFill(): Color
}

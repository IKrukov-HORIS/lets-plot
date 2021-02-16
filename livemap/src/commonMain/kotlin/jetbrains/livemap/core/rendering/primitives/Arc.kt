/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.livemap.core.rendering.primitives

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.values.Color
import jetbrains.datalore.vis.canvas.Context2d
import kotlin.math.PI

class Arc : RenderBox {
    override var origin: DoubleVector = DoubleVector.ZERO
        set(value) {
            field = value
            update()
        }

    override var dimension: DoubleVector = DoubleVector.ZERO
        set(value) {
            field = value
            update()
        }

    private var center: DoubleVector = DoubleVector.ZERO

    private fun update() {
        center = dimension.mul(0.5)
    }

    var strokeColor: Color? = null
    var strokeWidth: Double? = null
    var angle = PI / 2
    var startAngle = 0.0


    override fun render(ctx: Context2d) {
        ctx.beginPath()
        ctx.arc(center.x, center.y, dimension.x / 2.0, startAngle, startAngle + angle)

        strokeWidth?.let { ctx.setLineWidth(it) }
        strokeColor?.let { ctx.setStrokeStyle(it) }
        ctx.stroke()
    }
}
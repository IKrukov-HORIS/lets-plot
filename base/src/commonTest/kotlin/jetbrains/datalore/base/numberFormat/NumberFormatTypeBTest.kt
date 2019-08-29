package jetbrains.datalore.base.numberFormat

import kotlin.test.Test
import kotlin.test.assertEquals

class NumberFormatTypeBTest {
    @Test
    fun binary() {
        assertEquals("1010", NumberFormat("b").apply(10))
    }

    @Test
    fun binaryWithPrefix() {
        assertEquals("0b1010", NumberFormat("#b").apply(10))
    }
}
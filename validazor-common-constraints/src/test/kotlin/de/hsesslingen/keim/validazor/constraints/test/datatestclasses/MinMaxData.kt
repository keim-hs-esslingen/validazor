package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.Max
import de.hsesslingen.keim.validazor.constraints.Min
import java.math.BigDecimal
import java.math.BigInteger


class MinMaxData(
    @field:Max(MAX_VALID_NUMBER)
    @field:Min(MIN_VALID_NUMBER)
    val byteValue: Byte? = null,

    @field:Max(MAX_VALID_NUMBER)
    @field:Min(MIN_VALID_NUMBER)
    val shortValue: Short? = null,

    @field:Max(MAX_VALID_NUMBER)
    @field:Min(MIN_VALID_NUMBER)
    val intValue: Int? = null,

    @field:Max(MAX_VALID_NUMBER)
    @field:Min(MIN_VALID_NUMBER)
    val longValue: Long? = null,

    @field:Max(MAX_VALID_NUMBER)
    @field:Min(MIN_VALID_NUMBER)
    val bigIntegerValue: BigInteger? = null,

    @field:Max(MAX_VALID_NUMBER)
    @field:Min(MIN_VALID_NUMBER)
    val bigDecimalValue: BigDecimal? = null,

    @field:Max(MAX_VALID_NUMBER) // Support for floating points is optional by API
    @field:Min(MIN_VALID_NUMBER) // Support for floating points is optional by API
    val doubleValue: Double? = null,

    @field:Max(MAX_VALID_NUMBER) // Support for floating points is optional by API
    @field:Min(MIN_VALID_NUMBER) // Support for floating points is optional by API
    val floatValue: Float? = null,
) {
    companion object {
        /**
         * A number, small enough to fit in a byte easily.
         */
        const val MAX_VALID_NUMBER = 16L
        private const val MAX_VALID_NUMBER_STR = "16"
        const val MIN_VALID_NUMBER = -8L
        private const val MIN_VALID_NUMBER_STR = "-8"
    }
}
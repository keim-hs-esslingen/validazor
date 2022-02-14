package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.DecimalMax
import de.hsesslingen.keim.validazor.constraints.DecimalMin
import java.math.BigDecimal
import java.math.BigInteger


class DecimalMinMaxData(
    @field:DecimalMax(MAX_VALID_NUMBER_STR)
    @field:DecimalMin(MIN_VALID_NUMBER_STR)
    val byteValue: Byte? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR)
    @field:DecimalMin(MIN_VALID_NUMBER_STR)
    val shortValue: Short? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR)
    @field:DecimalMin(MIN_VALID_NUMBER_STR)
    val intValue: Int? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR)
    @field:DecimalMin(MIN_VALID_NUMBER_STR)
    val longValue: Long? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR)
    @field:DecimalMin(MIN_VALID_NUMBER_STR)
    val bigIntegerValue: BigInteger? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR)
    @field:DecimalMin(MIN_VALID_NUMBER_STR)
    val bigDecimalValue: BigDecimal? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR) // Support for floating points is optional by API
    @field:DecimalMin(MIN_VALID_NUMBER_STR) // Support for floating points is optional by API
    val doubleValue: Double? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR) // Support for floating points is optional by API
    @field:DecimalMin(MIN_VALID_NUMBER_STR) // Support for floating points is optional by API
    val floatValue: Float? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR)
    @field:DecimalMin(MIN_VALID_NUMBER_STR)
    val stringValue: String? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR)
    @field:DecimalMin(MIN_VALID_NUMBER_STR)
    val charSequenceValue: CharSequence? = null,




    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val byteValueExclusive: Byte? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val shortValueExclusive: Short? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val intValueExclusive: Int? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val longValueExclusive: Long? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val bigIntegerValueExclusive: BigInteger? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val bigDecimalValueExclusive: BigDecimal? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false) // Support for floating points is optional by API
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false) // Support for floating points is optional by API
    val doubleValueExclusive: Double? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false) // Support for floating points is optional by API
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false) // Support for floating points is optional by API
    val floatValueExclusive: Float? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val stringValueExclusive: String? = null,

    @field:DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @field:DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val charSequenceValueExclusive: CharSequence? = null,

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
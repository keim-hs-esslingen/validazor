package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.DecimalMax
import de.hsesslingen.keim.validazor.constraints.DecimalMin
import java.math.BigDecimal
import java.math.BigInteger


class DecimalMinMaxData(
    @DecimalMax(MAX_VALID_NUMBER_STR)
    @DecimalMin(MIN_VALID_NUMBER_STR)
    val byteValue: Byte? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR)
    @DecimalMin(MIN_VALID_NUMBER_STR)
    val shortValue: Short? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR)
    @DecimalMin(MIN_VALID_NUMBER_STR)
    val intValue: Int? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR)
    @DecimalMin(MIN_VALID_NUMBER_STR)
    val longValue: Long? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR)
    @DecimalMin(MIN_VALID_NUMBER_STR)
    val bigIntegerValue: BigInteger? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR)
    @DecimalMin(MIN_VALID_NUMBER_STR)
    val bigDecimalValue: BigDecimal? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR) // Support for floating points is optional by API
    @DecimalMin(MIN_VALID_NUMBER_STR) // Support for floating points is optional by API
    val doubleValue: Double? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR) // Support for floating points is optional by API
    @DecimalMin(MIN_VALID_NUMBER_STR) // Support for floating points is optional by API
    val floatValue: Float? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR)
    @DecimalMin(MIN_VALID_NUMBER_STR)
    val stringValue: String? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR)
    @DecimalMin(MIN_VALID_NUMBER_STR)
    val charSequenceValue: CharSequence? = null,




    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val byteValueExclusive: Byte? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val shortValueExclusive: Short? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val intValueExclusive: Int? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val longValueExclusive: Long? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val bigIntegerValueExclusive: BigInteger? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val bigDecimalValueExclusive: BigDecimal? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false) // Support for floating points is optional by API
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false) // Support for floating points is optional by API
    val doubleValueExclusive: Double? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false) // Support for floating points is optional by API
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false) // Support for floating points is optional by API
    val floatValueExclusive: Float? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
    val stringValueExclusive: String? = null,

    @DecimalMax(MAX_VALID_NUMBER_STR, inclusive = false)
    @DecimalMin(MIN_VALID_NUMBER_STR, inclusive = false)
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
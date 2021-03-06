package de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses

import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.math.BigInteger


class DigitsData(
    @field:Digits(integer = MAX_INTEGER_DIGITS, fraction = MAX_FRACTION_DIGITS)
    val byteValue: Byte? = null,

    @field:Digits(integer = MAX_INTEGER_DIGITS, fraction = MAX_FRACTION_DIGITS)
    val shortValue: Short? = null,

    @field:Digits(integer = MAX_INTEGER_DIGITS, fraction = MAX_FRACTION_DIGITS)
    val intValue: Int? = null,

    @field:Digits(integer = MAX_INTEGER_DIGITS, fraction = MAX_FRACTION_DIGITS)
    val longValue: Long? = null,

    @field:Digits(integer = MAX_INTEGER_DIGITS, fraction = MAX_FRACTION_DIGITS)
    val bigIntegerValue: BigInteger? = null,

    @field:Digits(integer = MAX_INTEGER_DIGITS, fraction = MAX_FRACTION_DIGITS)
    val bigDecimalValue: BigDecimal? = null,

    @field:Digits(integer = MAX_INTEGER_DIGITS, fraction = MAX_FRACTION_DIGITS)
    val doubleValue: Double? = null,

    @field:Digits(integer = MAX_INTEGER_DIGITS, fraction = MAX_FRACTION_DIGITS)
    val floatValue: Float? = null,

    @field:Digits(integer = MAX_CRAZY_INTEGER_DIGITS, fraction = MAX_CRAZY_FRACTION_DIGITS)
    val bigDecimalCrazyValue: BigDecimal? = null,

    @field:Digits(integer = MAX_CRAZY_INTEGER_DIGITS, fraction = MAX_CRAZY_FRACTION_DIGITS)
    val doubleCrazyValue: Double? = null,
) {
    companion object {
        const val MAX_INTEGER_DIGITS = 2
        const val MAX_FRACTION_DIGITS = 2
        const val VALID_INT_VALUE = 12
        const val TOO_HIGH_INT_VALUE = 120
        const val VALID_FRACTION_VALUE = 20.02
        const val TOO_PRECISE_FRACTION_VALUE = 20.002

        const val MAX_CRAZY_INTEGER_DIGITS = 7
        const val MAX_CRAZY_FRACTION_DIGITS = 6
        const val VALID_CRAZY_VALUE = 761314.38636
        const val INVALID_CRAZY_VALUE = 98761314.3863684
    }
}
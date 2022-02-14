package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.Negative
import de.hsesslingen.keim.validazor.constraints.NegativeOrZero
import de.hsesslingen.keim.validazor.constraints.Positive
import de.hsesslingen.keim.validazor.constraints.PositiveOrZero
import java.math.BigDecimal
import java.math.BigInteger

class PositiveNegativeData(
    @Negative
    val negativeByte: Byte? = null,
    @NegativeOrZero
    val negativeOrZeroByte: Byte? = null,
    @Positive
    val positiveByte: Byte? = null,
    @PositiveOrZero
    val positiveOrZeroByte: Byte? = null,

    @Negative
    val negativeShort: Short? = null,
    @NegativeOrZero
    val negativeOrZeroShort: Short? = null,
    @Positive
    val positiveShort: Short? = null,
    @PositiveOrZero
    val positiveOrZeroShort: Short? = null,

    @Negative
    val negativeInt: Int? = null,
    @NegativeOrZero
    val negativeOrZeroInt: Int? = null,
    @Positive
    val positiveInt: Int? = null,
    @PositiveOrZero
    val positiveOrZeroInt: Int? = null,

    @Negative
    val negativeLong: Long? = null,
    @NegativeOrZero
    val negativeOrZeroLong: Long? = null,
    @Positive
    val positiveLong: Long? = null,
    @PositiveOrZero
    val positiveOrZeroLong: Long? = null,

    @Negative
    val negativeFloat: Float? = null,
    @NegativeOrZero
    val negativeOrZeroFloat: Float? = null,
    @Positive
    val positiveFloat: Float? = null,
    @PositiveOrZero
    val positiveOrZeroFloat: Float? = null,

    @Negative
    val negativeDouble: Double? = null,
    @NegativeOrZero
    val negativeOrZeroDouble: Double? = null,
    @Positive
    val positiveDouble: Double? = null,
    @PositiveOrZero
    val positiveOrZeroDouble: Double? = null,

    @Negative
    val negativeBigInteger: BigInteger? = null,
    @NegativeOrZero
    val negativeOrZeroBigInteger: BigInteger? = null,
    @Positive
    val positiveBigInteger: BigInteger? = null,
    @PositiveOrZero
    val positiveOrZeroBigInteger: BigInteger? = null,

    @Negative
    val negativeBigDecimal: BigDecimal? = null,
    @NegativeOrZero
    val negativeOrZeroBigDecimal: BigDecimal? = null,
    @Positive
    val positiveBigDecimal: BigDecimal? = null,
    @PositiveOrZero
    val positiveOrZeroBigDecimal: BigDecimal? = null,
)
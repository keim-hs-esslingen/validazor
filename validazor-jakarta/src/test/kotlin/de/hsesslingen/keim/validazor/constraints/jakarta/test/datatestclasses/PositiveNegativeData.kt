package de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses

import jakarta.validation.constraints.Negative
import jakarta.validation.constraints.NegativeOrZero
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal
import java.math.BigInteger

class PositiveNegativeData(
    @field:Negative
    val negativeByte: Byte? = null,
    @field:NegativeOrZero
    val negativeOrZeroByte: Byte? = null,
    @field:Positive
    val positiveByte: Byte? = null,
    @field:PositiveOrZero
    val positiveOrZeroByte: Byte? = null,

    @field:Negative
    val negativeShort: Short? = null,
    @field:NegativeOrZero
    val negativeOrZeroShort: Short? = null,
    @field:Positive
    val positiveShort: Short? = null,
    @field:PositiveOrZero
    val positiveOrZeroShort: Short? = null,

    @field:Negative
    val negativeInt: Int? = null,
    @field:NegativeOrZero
    val negativeOrZeroInt: Int? = null,
    @field:Positive
    val positiveInt: Int? = null,
    @field:PositiveOrZero
    val positiveOrZeroInt: Int? = null,

    @field:Negative
    val negativeLong: Long? = null,
    @field:NegativeOrZero
    val negativeOrZeroLong: Long? = null,
    @field:Positive
    val positiveLong: Long? = null,
    @field:PositiveOrZero
    val positiveOrZeroLong: Long? = null,

    @field:Negative
    val negativeFloat: Float? = null,
    @field:NegativeOrZero
    val negativeOrZeroFloat: Float? = null,
    @field:Positive
    val positiveFloat: Float? = null,
    @field:PositiveOrZero
    val positiveOrZeroFloat: Float? = null,

    @field:Negative
    val negativeDouble: Double? = null,
    @field:NegativeOrZero
    val negativeOrZeroDouble: Double? = null,
    @field:Positive
    val positiveDouble: Double? = null,
    @field:PositiveOrZero
    val positiveOrZeroDouble: Double? = null,

    @field:Negative
    val negativeBigInteger: BigInteger? = null,
    @field:NegativeOrZero
    val negativeOrZeroBigInteger: BigInteger? = null,
    @field:Positive
    val positiveBigInteger: BigInteger? = null,
    @field:PositiveOrZero
    val positiveOrZeroBigInteger: BigInteger? = null,

    @field:Negative
    val negativeBigDecimal: BigDecimal? = null,
    @field:NegativeOrZero
    val negativeOrZeroBigDecimal: BigDecimal? = null,
    @field:Positive
    val positiveBigDecimal: BigDecimal? = null,
    @field:PositiveOrZero
    val positiveOrZeroBigDecimal: BigDecimal? = null,
)
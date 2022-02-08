package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertMessage
import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertValid
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DecimalMinMaxData
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DecimalMinMaxData.Companion.MAX_VALID_NUMBER
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DecimalMinMaxData.Companion.MIN_VALID_NUMBER
import de.hsesslingen.keim.validazor.constraints.jakarta.test.validate
import org.junit.jupiter.api.Test

class DecimalMinMaxTest {

    @Test
    fun testNullValid() {
        assertValid(
            DecimalMinMaxData(
                byteValue = null,
                shortValue = null,
                intValue = null,
                longValue = null,
                floatValue = null,
                doubleValue = null,
                bigDecimalValue = null,
                bigIntegerValue = null,
                stringValue = null,
                charSequenceValue = null,
            )
        )
    }

    @Test
    fun testMinValid() {
        assertValid(
            DecimalMinMaxData(
                byteValue = MIN_VALID_NUMBER.toByte(),
                shortValue = MIN_VALID_NUMBER.toShort(),
                intValue = MIN_VALID_NUMBER.toInt(),
                longValue = MIN_VALID_NUMBER,
                floatValue = MIN_VALID_NUMBER.toFloat(),
                doubleValue = MIN_VALID_NUMBER.toDouble(),
                bigDecimalValue = MIN_VALID_NUMBER.toBigDecimal(),
                bigIntegerValue = MIN_VALID_NUMBER.toBigInteger(),
                stringValue = MIN_VALID_NUMBER.toString(),
                charSequenceValue = MIN_VALID_NUMBER.toString(),
            )
        )
    }

    @Test
    fun testMaxValid() {
        assertValid(
            DecimalMinMaxData(
                byteValue = MAX_VALID_NUMBER.toByte(),
                shortValue = MAX_VALID_NUMBER.toShort(),
                intValue = MAX_VALID_NUMBER.toInt(),
                longValue = MAX_VALID_NUMBER,
                floatValue = MAX_VALID_NUMBER.toFloat(),
                doubleValue = MAX_VALID_NUMBER.toDouble(),
                bigDecimalValue = MAX_VALID_NUMBER.toBigDecimal(),
                bigIntegerValue = MAX_VALID_NUMBER.toBigInteger(),
                stringValue = MAX_VALID_NUMBER.toString(),
                charSequenceValue = MAX_VALID_NUMBER.toString(),
            )
        )
    }

    @Test
    fun testTooHighValueInvalid() {
        val value = MAX_VALID_NUMBER + 1

        val violations = validate(
            DecimalMinMaxData(
                byteValue = value.toByte(),
                shortValue = value.toShort(),
                intValue = value.toInt(),
                longValue = value,
                floatValue = value.toFloat(),
                doubleValue = value.toDouble(),
                bigDecimalValue = value.toBigDecimal(),
                bigIntegerValue = value.toBigInteger(),
                stringValue = value.toString(),
                charSequenceValue = value.toString(),
            )
        )

        violations.assertMessage("must be less than or equal to $MAX_VALID_NUMBER")
            .presentFor("byteValue")
            .presentFor("shortValue")
            .presentFor("intValue")
            .presentFor("longValue")
            .presentFor("floatValue")
            .presentFor("doubleValue")
            .presentFor("bigDecimalValue")
            .presentFor("bigIntegerValue")
    }

    @Test
    fun testTooLowValueInvalid() {
        val value = MIN_VALID_NUMBER - 1

        val violations = validate(
            DecimalMinMaxData(
                byteValue = value.toByte(),
                shortValue = value.toShort(),
                intValue = value.toInt(),
                longValue = value,
                floatValue = value.toFloat(),
                doubleValue = value.toDouble(),
                bigDecimalValue = value.toBigDecimal(),
                bigIntegerValue = value.toBigInteger(),
                stringValue = value.toString(),
                charSequenceValue = value.toString(),
            )
        )

        violations.assertMessage("must be greater than or equal to $MIN_VALID_NUMBER")
            .presentFor("byteValue")
            .presentFor("shortValue")
            .presentFor("intValue")
            .presentFor("longValue")
            .presentFor("floatValue")
            .presentFor("doubleValue")
            .presentFor("bigDecimalValue")
            .presentFor("bigIntegerValue")
    }


    @Test
    fun testTooHighExclusiveValueInvalid() {
        val value = MAX_VALID_NUMBER

        val violations = validate(
            DecimalMinMaxData(
                byteValueExclusive = value.toByte(),
                shortValueExclusive = value.toShort(),
                intValueExclusive = value.toInt(),
                longValueExclusive = value,
                floatValueExclusive = value.toFloat(),
                doubleValueExclusive = value.toDouble(),
                bigDecimalValueExclusive = value.toBigDecimal(),
                bigIntegerValueExclusive = value.toBigInteger(),
                stringValueExclusive = value.toString(),
                charSequenceValueExclusive = value.toString(),
            )
        )

        violations.assertMessage("must be less than $MAX_VALID_NUMBER")
            .presentFor("byteValueExclusive")
            .presentFor("shortValueExclusive")
            .presentFor("intValueExclusive")
            .presentFor("longValueExclusive")
            .presentFor("floatValueExclusive")
            .presentFor("doubleValueExclusive")
            .presentFor("bigDecimalValueExclusive")
            .presentFor("bigIntegerValueExclusive")
    }

    @Test
    fun testTooLowExclusiveValueInvalid() {
        val value = MIN_VALID_NUMBER

        val violations = validate(
            DecimalMinMaxData(
                byteValueExclusive = value.toByte(),
                shortValueExclusive = value.toShort(),
                intValueExclusive = value.toInt(),
                longValueExclusive = value,
                floatValueExclusive = value.toFloat(),
                doubleValueExclusive = value.toDouble(),
                bigDecimalValueExclusive = value.toBigDecimal(),
                bigIntegerValueExclusive = value.toBigInteger(),
                stringValueExclusive = value.toString(),
                charSequenceValueExclusive = value.toString(),
            )
        )

        violations.assertMessage("must be greater than $MIN_VALID_NUMBER")
            .presentFor("byteValueExclusive")
            .presentFor("shortValueExclusive")
            .presentFor("intValueExclusive")
            .presentFor("longValueExclusive")
            .presentFor("floatValueExclusive")
            .presentFor("doubleValueExclusive")
            .presentFor("bigDecimalValueExclusive")
            .presentFor("bigIntegerValueExclusive")
    }
}
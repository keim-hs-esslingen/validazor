package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertValid
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.MinMaxData
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.MinMaxData.Companion.MAX_VALID_NUMBER
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.MinMaxData.Companion.MIN_VALID_NUMBER
import de.hsesslingen.keim.validazor.constraints.jakarta.test.validate
import de.hsesslingen.keim.validazor.test.assertMessage
import org.junit.jupiter.api.Test

class MinMaxTest {

    @Test
    fun testNullValid() {
        assertValid(
            MinMaxData(
                byteValue = null,
                shortValue = null,
                intValue = null,
                longValue = null,
                floatValue = null,
                doubleValue = null,
                bigDecimalValue = null,
                bigIntegerValue = null,
            )
        )
    }

    @Test
    fun testMinValid() {
        assertValid(
            MinMaxData(
                byteValue = MIN_VALID_NUMBER.toByte(),
                shortValue = MIN_VALID_NUMBER.toShort(),
                intValue = MIN_VALID_NUMBER.toInt(),
                longValue = MIN_VALID_NUMBER,
                floatValue = MIN_VALID_NUMBER.toFloat(),
                doubleValue = MIN_VALID_NUMBER.toDouble(),
                bigDecimalValue = MIN_VALID_NUMBER.toBigDecimal(),
                bigIntegerValue = MIN_VALID_NUMBER.toBigInteger(),
            )
        )
    }

    @Test
    fun testMaxValid() {
        assertValid(
            MinMaxData(
                byteValue = MAX_VALID_NUMBER.toByte(),
                shortValue = MAX_VALID_NUMBER.toShort(),
                intValue = MAX_VALID_NUMBER.toInt(),
                longValue = MAX_VALID_NUMBER,
                floatValue = MAX_VALID_NUMBER.toFloat(),
                doubleValue = MAX_VALID_NUMBER.toDouble(),
                bigDecimalValue = MAX_VALID_NUMBER.toBigDecimal(),
                bigIntegerValue = MAX_VALID_NUMBER.toBigInteger(),
            )
        )
    }

    @Test
    fun testTooHighValueInvalid() {
        val value = MAX_VALID_NUMBER + 1

        val violations = validate(
            MinMaxData(
                byteValue = value.toByte(),
                shortValue = value.toShort(),
                intValue = value.toInt(),
                longValue = value,
                floatValue = value.toFloat(),
                doubleValue = value.toDouble(),
                bigDecimalValue = value.toBigDecimal(),
                bigIntegerValue = value.toBigInteger(),
            )
        )

        violations.assertMessage("must be less than $MAX_VALID_NUMBER")
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
            MinMaxData(
                byteValue = value.toByte(),
                shortValue = value.toShort(),
                intValue = value.toInt(),
                longValue = value,
                floatValue = value.toFloat(),
                doubleValue = value.toDouble(),
                bigDecimalValue = value.toBigDecimal(),
                bigIntegerValue = value.toBigInteger(),
            )
        )

        violations.assertMessage("must be greater than $MIN_VALID_NUMBER")
            .presentFor("byteValue")
            .presentFor("shortValue")
            .presentFor("intValue")
            .presentFor("longValue")
            .presentFor("floatValue")
            .presentFor("doubleValue")
            .presentFor("bigDecimalValue")
            .presentFor("bigIntegerValue")
    }
}
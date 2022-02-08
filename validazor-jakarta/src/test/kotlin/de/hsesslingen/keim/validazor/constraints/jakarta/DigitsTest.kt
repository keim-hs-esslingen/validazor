package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertMessage
import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertValid
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.INVALID_CRAZY_VALUE
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.MAX_CRAZY_FRACTION_DIGITS
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.MAX_CRAZY_INTEGER_DIGITS
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.MAX_FRACTION_DIGITS
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.MAX_INTEGER_DIGITS
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.TOO_HIGH_INT_VALUE
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.TOO_PRECISE_FRACTION_VALUE
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.VALID_CRAZY_VALUE
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.VALID_FRACTION_VALUE
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.DigitsData.Companion.VALID_INT_VALUE
import de.hsesslingen.keim.validazor.constraints.jakarta.test.validate
import org.junit.jupiter.api.Test

class DigitsTest {

    @Test
    fun testNullValid() {
        assertValid(
            DigitsData(
                byteValue = null,
                shortValue = null,
                intValue = null,
                longValue = null,
                bigDecimalValue = null,
                bigIntegerValue = null,
                doubleValue = null,
                floatValue = null,
            )
        )
    }

    @Test
    fun testIntegerValid() {
        val value = VALID_INT_VALUE

        assertValid(
            DigitsData(
                byteValue = value.toByte(),
                shortValue = value.toShort(),
                intValue = value,
                longValue = value.toLong(),
                bigDecimalValue = value.toBigDecimal(),
                bigIntegerValue = value.toBigInteger(),
                doubleValue = value.toDouble(),
                floatValue = value.toFloat(),
            )
        )
    }

    @Test
    fun testFractionValid() {
        val value = VALID_FRACTION_VALUE

        assertValid(
            DigitsData(
                bigDecimalValue = value.toBigDecimal(),
                doubleValue = value,
                floatValue = value.toFloat(),
            )
        )
    }

    @Test
    fun testTooManyIntegerDigitsInvalid() {
        val value = TOO_HIGH_INT_VALUE

        val violations = validate(
            DigitsData(
                byteValue = value.toByte(),
                shortValue = value.toShort(),
                intValue = value,
                longValue = value.toLong(),
                bigDecimalValue = value.toBigDecimal(),
                bigIntegerValue = value.toBigInteger(),
                doubleValue = value.toDouble(),
                floatValue = value.toFloat(),
            )
        )

        violations.assertMessage("must have at most $MAX_INTEGER_DIGITS integer digits and $MAX_FRACTION_DIGITS fractional digits")
            .presentFor("byteValue")
            .presentFor("shortValue")
            .presentFor("intValue")
            .presentFor("longValue")
            .presentFor("bigDecimalValue")
            .presentFor("bigIntegerValue")
            .presentFor("doubleValue")
            .presentFor("floatValue")
    }

    @Test
    fun testTooManyFractionalDigitsInvalid() {
        val value = TOO_PRECISE_FRACTION_VALUE

        val violations = validate(
            DigitsData(
                bigDecimalValue = value.toBigDecimal(),
                doubleValue = value,
                floatValue = value.toFloat(),
            )
        )

        violations.assertMessage("must have at most $MAX_INTEGER_DIGITS integer digits and $MAX_FRACTION_DIGITS fractional digits")
            .presentFor("bigDecimalValue")
            .presentFor("doubleValue")
            .presentFor("floatValue")
    }

    @Test
    fun testCrazyValueValid() {
        val value = VALID_CRAZY_VALUE

        assertValid(
            DigitsData(
                bigDecimalCrazyValue = value.toBigDecimal(),
                doubleCrazyValue = value,
            )
        )
    }

    @Test
    fun testCrazyValueInvalid() {
        val value = INVALID_CRAZY_VALUE

        val violations = validate(
            DigitsData(
                bigDecimalCrazyValue = value.toBigDecimal(),
                doubleCrazyValue = value,
            )
        )

        violations.assertMessage("must have at most $MAX_CRAZY_INTEGER_DIGITS integer digits and $MAX_CRAZY_FRACTION_DIGITS fractional digits")
            .presentFor("bigDecimalCrazyValue")
            .presentFor("doubleCrazyValue")
    }
}
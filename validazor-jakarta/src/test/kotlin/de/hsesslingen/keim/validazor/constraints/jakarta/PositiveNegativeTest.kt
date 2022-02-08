package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertMessage
import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertValid
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.PositiveNegativeData
import de.hsesslingen.keim.validazor.constraints.jakarta.test.validate
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigInteger

class PositiveNegativeTest {

    @Test
    fun testNullValid() {
        assertValid(
            PositiveNegativeData(
                positiveByte = null,
                positiveOrZeroByte = null,
                positiveShort = null,
                positiveOrZeroShort = null,
                positiveInt = null,
                positiveOrZeroInt = null,
                positiveLong = null,
                positiveOrZeroLong = null,
                positiveFloat = null,
                positiveOrZeroFloat = null,
                positiveDouble = null,
                positiveOrZeroDouble = null,
                positiveBigInteger = null,
                positiveOrZeroBigInteger = null,
                positiveBigDecimal = null,
                positiveOrZeroBigDecimal = null,
                negativeByte = null,
                negativeOrZeroByte = null,
                negativeShort = null,
                negativeOrZeroShort = null,
                negativeInt = null,
                negativeOrZeroInt = null,
                negativeLong = null,
                negativeOrZeroLong = null,
                negativeFloat = null,
                negativeOrZeroFloat = null,
                negativeDouble = null,
                negativeOrZeroDouble = null,
                negativeBigInteger = null,
                negativeOrZeroBigInteger = null,
                negativeBigDecimal = null,
                negativeOrZeroBigDecimal = null,
            )
        )
    }

    @Test
    fun testValid() {
        assertValid(
            PositiveNegativeData(
                positiveByte = 1,
                positiveOrZeroByte = 0,
                positiveShort = 1,
                positiveOrZeroShort = 0,
                positiveInt = 1,
                positiveOrZeroInt = 0,
                positiveLong = 1,
                positiveOrZeroLong = 0,
                positiveFloat = Float.MIN_VALUE,
                positiveOrZeroFloat = 0.0f,
                positiveDouble = Double.MIN_VALUE,
                positiveOrZeroDouble = 0.0,
                positiveBigInteger = BigInteger.ONE,
                positiveOrZeroBigInteger = BigInteger.ZERO,
                positiveBigDecimal = BigDecimal.valueOf(Double.MIN_VALUE),
                positiveOrZeroBigDecimal = BigDecimal.ZERO,
                negativeByte = -1,
                negativeOrZeroByte = 0,
                negativeShort = -1,
                negativeOrZeroShort = 0,
                negativeInt = -1,
                negativeOrZeroInt = 0,
                negativeLong = -1,
                negativeOrZeroLong = 0,
                negativeFloat = -Float.MIN_VALUE,
                negativeOrZeroFloat = 0.0f,
                negativeDouble = -Double.MIN_VALUE,
                negativeOrZeroDouble = 0.0,
                negativeBigInteger = BigInteger.valueOf(-1),
                negativeOrZeroBigInteger = BigInteger.ZERO,
                negativeBigDecimal = BigDecimal.valueOf(-Double.MIN_VALUE),
                negativeOrZeroBigDecimal = BigDecimal.ZERO,
            )
        )
    }

    @Test
    fun testInvalid() {
        val violations = validate(
            PositiveNegativeData(
                positiveByte = 0,
                positiveOrZeroByte = -1,
                positiveShort = 0,
                positiveOrZeroShort = -1,
                positiveInt = 0,
                positiveOrZeroInt = -1,
                positiveLong = 0,
                positiveOrZeroLong = -1,
                positiveFloat = 0.0f,
                positiveOrZeroFloat = -Float.MIN_VALUE,
                positiveDouble = 0.0,
                positiveOrZeroDouble = -Double.MIN_VALUE,
                positiveBigInteger = BigInteger.ZERO,
                positiveOrZeroBigInteger = BigInteger.valueOf(-1),
                positiveBigDecimal = BigDecimal.ZERO,
                positiveOrZeroBigDecimal = BigDecimal.valueOf(-Double.MIN_VALUE),
                negativeByte = 0,
                negativeOrZeroByte = 1,
                negativeShort = 0,
                negativeOrZeroShort = 1,
                negativeInt = 0,
                negativeOrZeroInt = 1,
                negativeLong = 0,
                negativeOrZeroLong = 1,
                negativeFloat = 0.0f,
                negativeOrZeroFloat = Float.MIN_VALUE,
                negativeDouble = 0.0,
                negativeOrZeroDouble = Double.MIN_VALUE,
                negativeBigInteger = BigInteger.ZERO,
                negativeOrZeroBigInteger = BigInteger.ONE,
                negativeBigDecimal = BigDecimal.ZERO,
                negativeOrZeroBigDecimal = BigDecimal.valueOf(Double.MIN_VALUE),
            )
        )

        violations.assertMessage("must be greater than zero")
            .presentFor("positiveByte")
            .presentFor("positiveShort")
            .presentFor("positiveInt")
            .presentFor("positiveLong")
            .presentFor("positiveFloat")
            .presentFor("positiveDouble")
            .presentFor("positiveBigInteger")
            .presentFor("positiveBigDecimal")

        violations.assertMessage("must be greater than or equal to zero")
            .presentFor("positiveOrZeroByte")
            .presentFor("positiveOrZeroShort")
            .presentFor("positiveOrZeroInt")
            .presentFor("positiveOrZeroLong")
            .presentFor("positiveOrZeroFloat")
            .presentFor("positiveOrZeroDouble")
            .presentFor("positiveOrZeroBigInteger")
            .presentFor("positiveOrZeroBigDecimal")

        violations.assertMessage("must be less than zero")
            .presentFor("negativeByte")
            .presentFor("negativeShort")
            .presentFor("negativeInt")
            .presentFor("negativeLong")
            .presentFor("negativeFloat")
            .presentFor("negativeDouble")
            .presentFor("negativeBigInteger")
            .presentFor("negativeBigDecimal")

        violations.assertMessage("must be less than or equal to zero")
            .presentFor("negativeOrZeroByte")
            .presentFor("negativeOrZeroShort")
            .presentFor("negativeOrZeroInt")
            .presentFor("negativeOrZeroLong")
            .presentFor("negativeOrZeroFloat")
            .presentFor("negativeOrZeroDouble")
            .presentFor("negativeOrZeroBigInteger")
            .presentFor("negativeOrZeroBigDecimal")
    }

    @Test
    fun testVariousInvalid() {
        val violations = validate(
            PositiveNegativeData(
                positiveByte = -1,
                positiveOrZeroByte = -2,
                positiveShort = -534,
                positiveOrZeroShort = -1453,
                positiveInt = -13245234,
                positiveOrZeroInt = -123453432,
                positiveLong = -23459879283,
                positiveOrZeroLong = -2345632451,
                positiveFloat = -1321.654f,
                positiveOrZeroFloat = -32.34345f,
                positiveDouble = -2345547.34562345,
                positiveOrZeroDouble = -456456.4563456,
                positiveBigInteger = BigInteger.valueOf(-234554672234),
                positiveOrZeroBigInteger = BigInteger.valueOf(-3454576582542),
                positiveBigDecimal = BigDecimal.valueOf(-5464562452435),
                positiveOrZeroBigDecimal = BigDecimal.valueOf(-987652132654),
                negativeByte = 122,
                negativeOrZeroByte = 112,
                negativeShort = 3452,
                negativeOrZeroShort = 234,
                negativeInt = 3234,
                negativeOrZeroInt = 2341,
                negativeLong = 23452346234,
                negativeOrZeroLong = 2347667978,
                negativeFloat = 234.2345f,
                negativeOrZeroFloat = 2345.456f,
                negativeDouble = 1342345.53689,
                negativeOrZeroDouble = 21345.59567,
                negativeBigInteger = BigInteger.valueOf(3421345234),
                negativeOrZeroBigInteger = BigInteger.valueOf(34255645),
                negativeBigDecimal = BigDecimal.valueOf(234523435.567856),
                negativeOrZeroBigDecimal = BigDecimal.valueOf(987.9876532168),
            )
        )

        violations.assertMessage("must be greater than zero")
            .presentFor("positiveByte")
            .presentFor("positiveShort")
            .presentFor("positiveInt")
            .presentFor("positiveLong")
            .presentFor("positiveFloat")
            .presentFor("positiveDouble")
            .presentFor("positiveBigInteger")
            .presentFor("positiveBigDecimal")

        violations.assertMessage("must be greater than or equal to zero")
            .presentFor("positiveOrZeroByte")
            .presentFor("positiveOrZeroShort")
            .presentFor("positiveOrZeroInt")
            .presentFor("positiveOrZeroLong")
            .presentFor("positiveOrZeroFloat")
            .presentFor("positiveOrZeroDouble")
            .presentFor("positiveOrZeroBigInteger")
            .presentFor("positiveOrZeroBigDecimal")

        violations.assertMessage("must be less than zero")
            .presentFor("negativeByte")
            .presentFor("negativeShort")
            .presentFor("negativeInt")
            .presentFor("negativeLong")
            .presentFor("negativeFloat")
            .presentFor("negativeDouble")
            .presentFor("negativeBigInteger")
            .presentFor("negativeBigDecimal")

        violations.assertMessage("must be less than or equal to zero")
            .presentFor("negativeOrZeroByte")
            .presentFor("negativeOrZeroShort")
            .presentFor("negativeOrZeroInt")
            .presentFor("negativeOrZeroLong")
            .presentFor("negativeOrZeroFloat")
            .presentFor("negativeOrZeroDouble")
            .presentFor("negativeOrZeroBigInteger")
            .presentFor("negativeOrZeroBigDecimal")
    }
}
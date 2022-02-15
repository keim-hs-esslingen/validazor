package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertValid
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.BooleanData
import de.hsesslingen.keim.validazor.constraints.jakarta.test.validate
import de.hsesslingen.keim.validazor.test.assertPresent
import org.junit.jupiter.api.Test

class BooleanTest {

    @Test
    fun testBooleanValid() {
        assertValid(
            BooleanData(
                trueValue = true,
                falseValue = false
            )
        )

        assertValid(
            BooleanData(
                trueValue = null,
                falseValue = null
            )
        )
    }

    @Test
    fun testBooleanValueInvalid() {
        val violations = validate(
            BooleanData(
                trueValue = false,
                falseValue = true
            )
        )

        violations
            .assertPresent("must be true", "trueValue")
            .assertPresent("must be false", "falseValue")
    }
}
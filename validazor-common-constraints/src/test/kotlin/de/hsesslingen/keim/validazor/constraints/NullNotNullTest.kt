package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.constraints.test.assertPresent
import de.hsesslingen.keim.validazor.constraints.test.assertValid
import de.hsesslingen.keim.validazor.constraints.test.datatestclasses.NullNotNullData
import de.hsesslingen.keim.validazor.constraints.test.validate
import org.junit.jupiter.api.Test


class NullNotNullTest {

    @Test
    fun testNullConstraintsValid() {
        assertValid(
            NullNotNullData(
                nullValue = null,
                notNullValue = Any()
            )
        )
    }

    @Test
    fun testNullConstraintsInvalid() {
        val violations = validate(
            NullNotNullData(
                nullValue = Any(),
                notNullValue = null
            )
        )

        violations
            .assertPresent("must be null", "nullValue")
            .assertPresent("must not be null", "notNullValue")
    }

}
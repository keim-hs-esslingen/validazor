package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.constraints.test.assertValid
import de.hsesslingen.keim.validazor.constraints.test.datatestclasses.PatternData
import de.hsesslingen.keim.validazor.constraints.test.datatestclasses.PatternData.Companion.PATTERN
import de.hsesslingen.keim.validazor.constraints.test.validate
import de.hsesslingen.keim.validazor.test.assertMessage
import org.junit.jupiter.api.Test

class PatternTest {

    @Test
    fun testValid() {
        assertValid(
            PatternData(
                stringValue = "abcXYZ",
                charSequenceValue = "abcXYZ",
            )
        )
    }

    @Test
    fun testInvalid() {
        val violations = validate(
            PatternData(
                stringValue = "0123456789!@#%^&",
                charSequenceValue = "0123456789!@#%^&",
            )
        )

        violations.assertMessage("must match pattern \"$PATTERN\"")
            .presentFor("stringValue")
            .presentFor("charSequenceValue")
    }

}
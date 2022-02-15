package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertValid
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.EmailData
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.EmailData.Companion.EXTRA_PATTERN
import de.hsesslingen.keim.validazor.constraints.jakarta.test.validate
import de.hsesslingen.keim.validazor.test.assertMessage
import org.junit.jupiter.api.Test

class EmailTest {

    @Test
    fun testValid() {
        assertValid(
            EmailData(
                stringValue = "john.doe@hs-esslingen.de",
                charSequenceValue = "john.doe@hs-esslingen.de",
                stringValueWithPattern = "abc@hs-esslingen.de",
                charSequenceValueWithPattern = "abc@hs-esslingen.de"
            )
        )

        assertValid(
            EmailData(
                stringValue = null,
                charSequenceValue = null,
                stringValueWithPattern = null,
                charSequenceValueWithPattern = null
            )
        )
    }

    @Test
    fun testInvalid() {
        val violations = validate(
            EmailData(
                stringValue = "abc",
                charSequenceValue = "abc",
                stringValueWithPattern = "abc",
                charSequenceValueWithPattern = "abc",
            )
        )

        violations.assertMessage("must be a valid email address")
            .presentFor("stringValue")
            .presentFor("charSequenceValue")

        violations.assertMessage("must be a valid email address and additionally match pattern \"$EXTRA_PATTERN\"")
            .presentFor("stringValueWithPattern")
            .presentFor("charSequenceValueWithPattern")
    }

    @Test
    fun testEmptyInvalid() {
        val violations = validate(
            EmailData(
                stringValue = "",
                charSequenceValue = "",
                stringValueWithPattern = "",
                charSequenceValueWithPattern = "",
            )
        )

        violations.assertMessage("must be a valid email address")
            .presentFor("stringValue")
            .presentFor("charSequenceValue")

        violations.assertMessage("must be a valid email address and additionally match pattern \"$EXTRA_PATTERN\"")
            .presentFor("stringValueWithPattern")
            .presentFor("charSequenceValueWithPattern")
    }


    @Test
    fun testExtraPatternInvalid() {
        val violations = validate(
            EmailData(
                stringValueWithPattern = "ddd@hs-esslingen.de",
                charSequenceValueWithPattern = "ddd@hs-esslingen.de",
            )
        )

        violations.assertMessage("must be a valid email address and additionally match pattern \"$EXTRA_PATTERN\"")
            .presentFor("stringValueWithPattern")
            .presentFor("charSequenceValueWithPattern")
    }
}
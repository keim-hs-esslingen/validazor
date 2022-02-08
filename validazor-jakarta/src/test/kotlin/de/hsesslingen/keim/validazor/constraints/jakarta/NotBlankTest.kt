package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertMessage
import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertValid
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.NotBlankData
import de.hsesslingen.keim.validazor.constraints.jakarta.test.validate
import org.junit.jupiter.api.Test


class NotBlankTest {

    @Test
    fun testNotBlankValid() {
        assertValid(
            NotBlankData(
                stringValue = "x",
                charSequenceValue = "x"
            )
        )

        assertValid(
            NotBlankData(
                stringValue = null,
                charSequenceValue = null
            )
        )
    }

    private fun testNotBlankInvalid(blankStringValue: String) {
        val violations = validate(
            NotBlankData(
                stringValue = blankStringValue,
                charSequenceValue = blankStringValue
            )
        )

        violations.assertMessage("must not be blank")
            .presentFor("stringValue")
            .presentFor("charSequenceValue")
    }

    @Test
    fun testNotBlankEmptyInvalid() {
        testNotBlankInvalid("")
    }

    @Test
    fun testNotBlankSpaceInvalid() {
        testNotBlankInvalid(" ")
        testNotBlankInvalid("  ")
    }

    @Test
    fun testNotBlankTabInvalid() {
        testNotBlankInvalid("\t")
        testNotBlankInvalid("\t\t")
    }

    @Test
    fun testNotBlankLineFeedInvalid() {
        testNotBlankInvalid("\n")
        testNotBlankInvalid("\r")
        testNotBlankInvalid("\n\r")
    }

    @Test
    fun testNotBlankVariousWhitespacesInvalid() {
        testNotBlankInvalid(" \t\n\r\n \t \t")
    }

}
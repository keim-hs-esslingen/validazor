package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.constraints.Password.CharacterKind.*
import de.hsesslingen.keim.validazor.constraints.test.validazor
import de.hsesslingen.keim.validazor.test.assertPresent
import de.hsesslingen.keim.validazor.test.assertPath
import de.hsesslingen.keim.validazor.test.startingWith
import org.junit.jupiter.api.Test

class PasswordTest {

    @Test
    fun testValidate1() {
        val violations = validazor().validate(TestObject1(""))

        violations.assertPath("password")
            .hasMessage("must be at least 2 characters long")
            .hasMessage("must contain characters of kind \"uppercase letters\"")
            .hasMessage("must contain characters of kind \"lowercase letters\"")
            .hasMessage("must contain characters of kind \"special characters\"")
            .hasMessage("must contain characters of kind \"digits\"")
    }

    @Test
    fun testValidate2() {
        val violations = validazor().validate(TestObject1("x"))

        violations.assertPath("password")
            .hasMessage("must be at least 2 characters long")
            .hasMessage("must contain characters of kind \"uppercase letters\"")
            .hasMessage("must contain characters of kind \"special characters\"")
            .hasMessage("must contain characters of kind \"digits\"")
    }

    @Test
    fun testValidate3() {
        val violations = validazor().validate(TestObject1("xX"))

        violations.assertPath("password")
            .hasMessage("must contain characters of kind \"special characters\"")
            .hasMessage("must contain characters of kind \"digits\"")
    }

    @Test
    fun testValidate4() {
        val violations = validazor().validate(TestObject1("xX12345"))

        violations.assertPath("password")
            .hasMessage("must contain characters of kind \"special characters\"")
            .hasMessage("must not be longer than 5 characters")
    }

    @Test
    fun testValidate5() {
        validazor().assertValid(TestObject1("xX1!"))
    }

    @Test
    fun testValidate6() {
        val violations = validazor().validate(TestObject1("aX1!"))

        violations.assertPresent("must not contain character 'a'", "password")
    }

    @Test
    fun testValidate7() {
        val violations = validazor().validate(TestObject2("aX1!"))

        violations.assertPath("password")
            .hasMessage("must not contain character 'X'")
            .hasMessage("must not contain character '1'")
            .hasMessage("must not contain character '!'")
    }

    @Test
    fun testValidate8() {
        val violations = validazor().validate(TestObject3("aX1!"))

        violations.assertPath("password")
            .hasMessage(startingWith("must have at least 120.0 bits of entropy"))
    }

    @Test
    fun testValidate9() {
        validazor().assertValid(TestObject3("aX1!asdc09234@#$%dfv$%^fgweS5"))
    }

    class TestObject1(
        @Password(
            minLength = 2,
            maxLength = 5,
            requiredCharacterKinds = [
                UPPERCASE_LETTERS,
                LOWERCASE_LETTERS,
                SPECIAL_CHARACTERS,
                DIGITS],
            forbiddenCharacters = ['a', 'b'],
        )
        val password: String
    )

    class TestObject2(
        @Password(allowedCharacters = ['a', 'b'])
        val password: String
    )

    class TestObject3(
        @Password(minEntropyBits = 120.0)
        val password: String
    )
}
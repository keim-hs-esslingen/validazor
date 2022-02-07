package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.Validazor
import de.hsesslingen.keim.validazor.constraints.Password.CharacterKind.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PasswordTest {

    fun createValidazor(): Validazor {
        return Validazor.Builder()
            .register(Password.Validator())
            .build()
    }

    @Test
    fun testValidate1() {
        val validazor = createValidazor()

        val violations = validazor.validate(TestObject1(""))

        assertTrue(violations.any { it.path == "password" && it.message == "must be at least 2 characters long" })
        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"uppercase letters\"" })
        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"lowercase letters\"" })
        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"special characters\"" })
        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"digits\"" })
    }

    @Test
    fun testValidate2() {
        val validazor = createValidazor()

        val violations = validazor.validate(TestObject1("x"))

        assertTrue(violations.any { it.path == "password" && it.message == "must be at least 2 characters long" })
        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"uppercase letters\"" })
        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"special characters\"" })
        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"digits\"" })
    }

    @Test
    fun testValidate3() {
        val validazor = createValidazor()

        val violations = validazor.validate(TestObject1("xX"))

        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"special characters\"" })
        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"digits\"" })
    }

    @Test
    fun testValidate4() {
        val validazor = createValidazor()

        val violations = validazor.validate(TestObject1("xX12345"))

        assertTrue(violations.any { it.path == "password" && it.message == "must contain characters of kind \"special characters\"" })
        assertTrue(violations.any { it.path == "password" && it.message == "must not be longer than 5 characters" })
    }

    @Test
    fun testValidate5() {
        val validazor = createValidazor()

        val violations = validazor.validate(TestObject1("xX1!"))

        assertTrue(violations.isEmpty())
    }

    @Test
    fun testValidate6() {
        val validazor = createValidazor()

        val violations = validazor.validate(TestObject1("aX1!"))

        assertTrue(violations.any { it.path == "password" && it.message == "must not contain character 'a'" })
    }

    @Test
    fun testValidate7() {
        val validazor = createValidazor()

        val violations = validazor.validate(TestObject2("aX1!"))

        assertTrue(violations.any { it.path == "password" && it.message == "must not contain character 'X'" })
        assertTrue(violations.any { it.path == "password" && it.message == "must not contain character '1'" })
        assertTrue(violations.any { it.path == "password" && it.message == "must not contain character '!'" })
    }

    @Test
    fun testValidate8() {
        val validazor = createValidazor()

        val violations = validazor.validate(TestObject3("aX1!"))

        assertTrue(violations.any { it.path == "password" && it.message.startsWith("must have at least 120.0 bits of entropy") })
    }

    @Test
    fun testValidate9() {
        val validazor = createValidazor()

        val violations = validazor.validate(TestObject3("aX1!asdc09234@#$%dfv$%^fgweS5"))

        assertTrue(violations.isEmpty())
    }

    class TestObject1(
        @field:Password(
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
        @field:Password(allowedCharacters = ['a', 'b'])
        val password: String
    )

    class TestObject3(
        @field:Password(minEntropyBits = 120.0)
        val password: String
    )
}
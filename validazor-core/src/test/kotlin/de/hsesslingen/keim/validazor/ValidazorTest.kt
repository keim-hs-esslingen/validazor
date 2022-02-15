package de.hsesslingen.keim.validazor

import de.hsesslingen.keim.validazor.testutils.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidazorTest {

    fun validazor(): Validazor.Builder {
        return Validazor.Builder()
            .register(NotNull.Validator())
            .register(ToStringEquals.Validator())
            .register(OlderThanFriends.Validator())
            .register(InhabitantsCorrectAddress.Validator())
    }

    private fun validPersonSimple(): Person {
        return Person(
            name = VALID_NAME,
            age = VALID_AGE,
            email = VALID_EMAIL,
            address = Address(
                street = VALID_STREET,
                city = VALID_CITY,
                zip = VALID_ZIP
            ),
            friends = listOf()
        )
    }

    private fun invalidPersonSimple(): Person {
        return Person(
            name = INVALID_NAME,
            age = INVALID_AGE,
            email = INVALID_EMAIL,
            address = Address(
                street = INVALID_STREET,
                city = INVALID_CITY,
                zip = INVALID_ZIP
            ),
            friends = listOf()
        )
    }

    private fun invalidPersonDeep(): Person {
        return invalidPersonSimple().apply {
            friends = listOf(
                validPersonSimple().apply { age = YOUNGER_AS_VALID_AGE },
                validPersonSimple().apply { age = OLDER_AS_VALID_AGE },
                invalidPersonSimple().apply {
                    friends = listOf(
                        validPersonSimple().apply { age = YOUNGER_AS_VALID_AGE },
                    )
                    address?.inhabitants = setOf(
                        validPersonSimple(),
                        validPersonSimple().apply { name = "John Smith" }
                    )
                    mappedFriends = mapOf(
                        validPersonSimple() to validPersonSimple(),
                        invalidPersonSimple() to invalidPersonSimple()
                    )
                }
            )
        }
    }

    private fun assertViolationsCorrectForInvalidPersonDeep(violations: Collection<Violation>, s: String) {
        assertTrue(violations.all { it.constraint != null })

        violations
            .assertPresent(OlderThanFriends.MSG, "")
            .assertPresent(INVALID_NAME_MSG, "name")
            .assertPresent(INVALID_AGE_MSG, "age")
            .assertPresent(INVALID_EMAIL_MSG, "email")
            .assertPresent(INVALID_STREET_MSG, "address${s}street")
            .assertPresent(INVALID_CITY_MSG, "address${s}city")
            .assertPresent(INVALID_ZIP_MSG, "address${s}zip")
            .assertPresent(INVALID_AGE_MSG, "friends[0]${s}age")
            .assertPresent(INVALID_AGE_MSG, "friends[1]${s}age")
            .assertPresent(INVALID_NAME_MSG, "friends[2]${s}name")
            .assertPresent(INVALID_AGE_MSG, "friends[2]${s}age")
            .assertPresent(INVALID_EMAIL_MSG, "friends[2]${s}email")
            .assertPresent(INVALID_STREET_MSG, "friends[2]${s}address${s}street")
            .assertPresent(INVALID_CITY_MSG, "friends[2]${s}address${s}city")
            .assertPresent(INVALID_ZIP_MSG, "friends[2]${s}address${s}zip")
            .assertPresent(INVALID_AGE_MSG, "friends[2]${s}friends[0]${s}age")
            .assertPresent(InhabitantsCorrectAddress.MSG, "friends[2]${s}address")
            .assertPresent(INVALID_NAME_MSG, "friends[2]${s}address${s}inhabitants[1]${s}name")
            .assertPresent(INVALID_NAME_MSG, "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}name")
            .assertPresent(INVALID_AGE_MSG, "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}age")
            .assertPresent(INVALID_EMAIL_MSG, "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}email")
            .assertPresent(
                INVALID_STREET_MSG,
                "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}address${s}street"
            )
            .assertPresent(INVALID_CITY_MSG, "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}address${s}city")
            .assertPresent(INVALID_ZIP_MSG, "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}address${s}zip")
            .assertPresent(INVALID_NAME_MSG, "friends[2]${s}mappedFriends[$INVALID_NAME]${s}name")
            .assertPresent(INVALID_AGE_MSG, "friends[2]${s}mappedFriends[$INVALID_NAME]${s}age")
            .assertPresent(INVALID_EMAIL_MSG, "friends[2]${s}mappedFriends[$INVALID_NAME]${s}email")
            .assertPresent(INVALID_STREET_MSG, "friends[2]${s}mappedFriends[$INVALID_NAME]${s}address${s}street")
            .assertPresent(INVALID_CITY_MSG, "friends[2]${s}mappedFriends[$INVALID_NAME]${s}address${s}city")
            .assertPresent(INVALID_ZIP_MSG, "friends[2]${s}mappedFriends[$INVALID_NAME]${s}address${s}zip")

            .assertNotPresent(OlderThanFriends.MSG, "friends[2]")
    }

    @Test
    fun testValidateValidPerson() {
        val validazor = validazor().build()
        val violations = validazor.validate(validPersonSimple())
        assertTrue(violations.isEmpty())
    }

    @Test
    fun testValidateInvalidPerson() {
        val person = invalidPersonDeep()
        val validazor = validazor().build()

        val violations = validazor.validate(person)
        assertViolationsCorrectForInvalidPersonDeep(violations, PropertyPath.DEFAULT_PATH_SEPARATOR)
    }

    @Test
    fun testValidateInvalidPersonWithCustomSeparator() {
        val separator = "|"
        val person = invalidPersonDeep()
        val validazor = validazor()
            .pathSeparator(separator)
            .build()

        val violations = validazor.validate(person)
        assertViolationsCorrectForInvalidPersonDeep(violations, separator)
    }

    @Test
    fun testValidateInvalidPersonWithLongCustomSeparator() {
        val separator = "--->"
        val person = invalidPersonDeep()
        val validazor = validazor()
            .pathSeparator(separator)
            .build()

        val violations = validazor.validate(person)
        assertViolationsCorrectForInvalidPersonDeep(violations, separator)
    }

    @Test
    fun testAssertValidValidPerson() {
        val person = validPersonSimple()
        val validazor = validazor().build()

        try {
            validazor.assertValid(person)
        } catch (ex: Exception) {
            fail("Unexpected exception occurred.", ex)
        }
    }

    @Test
    fun testAssertValidInvalidPerson() {
        val person = invalidPersonDeep()
        val validazor = validazor().build()

        try {
            validazor.assertValid(person)
            fail("Expected exception but non occurred.")
        } catch (ex: ViolationException) {
            assertViolationsCorrectForInvalidPersonDeep(ex.violations, PropertyPath.DEFAULT_PATH_SEPARATOR)
        } catch (ex: Exception) {
            fail("Unexpected exception occurred.", ex)
        }
    }

    @Test
    fun testIsValidValidPerson() {
        val person = validPersonSimple()
        val validazor = validazor().build()
        assertTrue(validazor.isValid(person))
    }

    @Test
    fun testIsValidInvalidPerson() {
        val person = invalidPersonDeep()
        val validazor = validazor().build()
        assertFalse(validazor.isValid(person))
    }
}
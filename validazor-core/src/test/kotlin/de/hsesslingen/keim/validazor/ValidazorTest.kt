package de.hsesslingen.keim.validazor

import de.hsesslingen.keim.validazor.testutil.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidazorTest {

    fun createValidazor(): Validazor.Builder {
        return Validazor.Builder()
            .register(NotNull.Validator())
            .register(ToStringEquals.Validator())
            .register(OlderThanFriends.Validator())
            .register(InhabitantsCorrectAddress.Validator())
    }

    fun validPersonSimple(): Person {
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

    fun invalidPersonSimple(): Person {
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

    fun invalidPersonDeep(): Person {
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

    fun assertViolationsCorrectForInvalidPersonDeep(violations: Collection<Violation>, s: String) {
        assertTrue(violations.isNotEmpty())
        assertTrue(violations.all { it.constraint != null })

        assertTrue(violations.any { it.path == "" && it.message == OlderThanFriends.MSG })
        assertTrue(violations.any { it.path == "name" && it.message == INVALID_NAME_MSG })
        assertTrue(violations.any { it.path == "age" && it.message == INVALID_AGE_MSG })
        assertTrue(violations.any { it.path == "email" && it.message == INVALID_EMAIL_MSG })
        assertTrue(violations.any { it.path == "address${s}street" && it.message == INVALID_STREET_MSG })
        assertTrue(violations.any { it.path == "address${s}city" && it.message == INVALID_CITY_MSG })
        assertTrue(violations.any { it.path == "address${s}zip" && it.message == INVALID_ZIP_MSG })

        assertTrue(violations.any { it.path == "friends[0]${s}age" && it.message == INVALID_AGE_MSG })
        assertTrue(violations.any { it.path == "friends[1]${s}age" && it.message == INVALID_AGE_MSG })

        assertTrue(violations.any { it.path == "friends[2]${s}name" && it.message == INVALID_NAME_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}age" && it.message == INVALID_AGE_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}email" && it.message == INVALID_EMAIL_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}address${s}street" && it.message == INVALID_STREET_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}address${s}city" && it.message == INVALID_CITY_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}address${s}zip" && it.message == INVALID_ZIP_MSG })

        assertTrue(violations.any { it.path == "friends[2]${s}friends[0]${s}age" && it.message == INVALID_AGE_MSG })
        assertTrue(violations.none { it.path == "friends[2]" && it.message == OlderThanFriends.MSG })

        assertTrue(violations.any { it.path == "friends[2]${s}address" && it.message == InhabitantsCorrectAddress.MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}address${s}inhabitants[1]${s}name" && it.message == INVALID_NAME_MSG })

        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}name" && it.message == INVALID_NAME_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}age" && it.message == INVALID_AGE_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}email" && it.message == INVALID_EMAIL_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}address${s}street" && it.message == INVALID_STREET_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}address${s}city" && it.message == INVALID_CITY_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends${s}keys[$INVALID_NAME]${s}address${s}zip" && it.message == INVALID_ZIP_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends[$INVALID_NAME]${s}name" && it.message == INVALID_NAME_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends[$INVALID_NAME]${s}age" && it.message == INVALID_AGE_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends[$INVALID_NAME]${s}email" && it.message == INVALID_EMAIL_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends[$INVALID_NAME]${s}address${s}street" && it.message == INVALID_STREET_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends[$INVALID_NAME]${s}address${s}city" && it.message == INVALID_CITY_MSG })
        assertTrue(violations.any { it.path == "friends[2]${s}mappedFriends[$INVALID_NAME]${s}address${s}zip" && it.message == INVALID_ZIP_MSG })
    }

    @Test
    fun testValidateValidPerson() {
        val person = validPersonSimple()
        val validazor = createValidazor().build()

        val violations = validazor.validate(person)

        assertTrue(violations.isEmpty())
    }

    @Test
    fun testValidateInvalidPerson() {
        val person = invalidPersonDeep()
        val validazor = createValidazor().build()

        val violations = validazor.validate(person)
        assertViolationsCorrectForInvalidPersonDeep(violations, PropertyPath.DEFAULT_PATH_SEPARATOR)
    }

    @Test
    fun testValidateInvalidPersonWithCustomSeparator() {
        val separator = "|"
        val person = invalidPersonDeep()
        val validazor = createValidazor()
            .pathSeparator(separator)
            .build()

        val violations = validazor.validate(person)
        assertViolationsCorrectForInvalidPersonDeep(violations, separator)
    }

    @Test
    fun testValidateInvalidPersonWithLongCustomSeparator() {
        val separator = "--->"
        val person = invalidPersonDeep()
        val validazor = createValidazor()
            .pathSeparator(separator)
            .build()

        val violations = validazor.validate(person)
        assertViolationsCorrectForInvalidPersonDeep(violations, separator)
    }

}
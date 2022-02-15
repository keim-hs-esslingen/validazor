package de.hsesslingen.keim.validazor.test.kotlin.dividableby

import de.hsesslingen.keim.validazor.Validazor
import de.hsesslingen.keim.validazor.Violation
import de.hsesslingen.keim.validazor.ViolationException
import de.hsesslingen.keim.validazor.registerDefaultValidators
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DividableByTest {

    @Test
    fun test() {
        // Create test object.
        val data = Data(intValue = 3, longValue = 4)

        val validazor = Validazor.Builder()
            // Here we register our custom constraint:
            .register(DividableBy.Validator())
            // If we also want the Jakarta constraints and the common ones from Validazor as well,
            // register the `DefaultValidators` using this extension function.
            // This is not required for custom constraints to work.
            .registerDefaultValidators()
            .build()

        // Validate and asses violations
        val violations = validazor.validate(data)
        println(violations)

        // Also available:
        val isValid = validazor.isValid(data)

        try {
            validazor.assertValid(data)
        } catch (ex: ViolationException) {
            println(ex.violations)
        }

        assertTrue(violations.any { (path, message): Violation -> path == "intValue" && message == "must be dividable by 2" })
        assertTrue(violations.any { (path, message): Violation -> path == "longValue" && message == "must be dividable by 3" })
    }

    fun testClassName() {
        val data = Data(intValue = 3, longValue = 4)

        val validazor = Validazor.Builder()
            .register(DividableBy.Validator())
            .build()

        val violations = validazor.validate(data)
        println(violations)
    }
}
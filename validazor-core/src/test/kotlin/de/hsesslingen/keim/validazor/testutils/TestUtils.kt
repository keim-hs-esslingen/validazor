package de.hsesslingen.keim.validazor.testutils

import de.hsesslingen.keim.validazor.Violation
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.fail

fun <T, C : Collection<T>> C.assertAny(test: (it: T) -> Boolean): C {
    assertTrue(this.any(test))
    return this
}

fun <T, C : Collection<T>> C.assertNone(test: (it: T) -> Boolean): C {
    val forbiddenViolation = this.firstOrNull(test) ?: return this // null value is expected.
    fail { "Encountered violation that was expected to NOT exist: $forbiddenViolation" }
}

fun <C : Collection<Violation>> C.assertPresent(message: String, `for path`: String): C {
    return this.assertAny { it.path == `for path` && it.message == message }
}

fun <C : Collection<Violation>> C.assertNotPresent(message: String, `for path`: String): C {
    return this.assertNone { it.path == `for path` && it.message == message }
}

package de.hsesslingen.keim.validazor.testutils

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue

fun <T, C : Collection<T>> C.assertAny(test: (it: T) -> Boolean): C {
    assertTrue(this.any(test))
    return this
}

fun <T, C : Collection<T>> C.assertNone(test: (it: T) -> Boolean): C {
    val forbiddenViolation = this.firstOrNull(test) ?: return this // null value is expected.
    fail { "Encountered violation that was expected to NOT exist: $forbiddenViolation" }
}
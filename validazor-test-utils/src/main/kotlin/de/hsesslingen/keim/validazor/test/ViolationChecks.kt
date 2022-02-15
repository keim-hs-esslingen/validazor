package de.hsesslingen.keim.validazor.test

import de.hsesslingen.keim.validazor.Violation
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.fail

fun <C : Collection<Violation>> C.assertAny(test: (it: Violation) -> Boolean): C {
    assertTrue(this.any(test))
    return this
}

fun <C : Collection<Violation>> C.assertNone(test: (it: Violation) -> Boolean): C {
    val forbiddenViolation = this.firstOrNull(test) ?: return this // null value is expected.
    fail { "Encountered violation that was expected to NOT exist: $forbiddenViolation" }
}

fun <C : Collection<Violation>> C.assertPresent(message: String, `for path`: String): C {
    return this.assertAny { it.path == `for path` && it.message == message }
}

fun <C : Collection<Violation>> C.assertPresent(messageTest: MessageTest, `for path`: String): C {
    return this.assertAny { it.path == `for path` && messageTest(it.message) }
}

fun <C : Collection<Violation>> C.assertNotPresent(message: String, `for path`: String): C {
    return this.assertNone { it.path == `for path` && it.message == message }
}

fun <C : Collection<Violation>> C.assertNotPresent(messageTest: MessageTest, `for path`: String): C {
    return this.assertNone { it.path == `for path` && messageTest(it.message) }
}

fun <C : Collection<Violation>> C.assertMessage(message: String): MessageCheck {
    return MessageCheck(this, message)
}

fun <C : Collection<Violation>> C.assertPath(path: String): PathCheck {
    return PathCheck(this, path)
}

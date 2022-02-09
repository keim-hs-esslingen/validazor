package de.hsesslingen.keim.validazor.test

import de.hsesslingen.keim.validazor.Violation
import de.hsesslingen.keim.validazor.testutils.assertAny
import de.hsesslingen.keim.validazor.testutils.assertNone

fun <C : Collection<Violation>> C.assertPresent(message: String, `for path`: String): C {
    return this.assertAny { it.path == `for path` && it.message == message }
}

fun <C : Collection<Violation>> C.assertNotPresent(message: String, `for path`: String): C {
    return this.assertNone { it.path == `for path` && it.message == message }
}

fun <C : Collection<Violation>> C.assertMessage(message: String): MessageCheck {
    return MessageCheck(this, message)
}

class MessageCheck(
    private val violations: Collection<Violation>,
    private val message: String
) {
    fun presentFor(path: String): MessageCheck {
        violations.assertPresent(message, path)
        return this
    }
    fun notPresentFor(path: String): MessageCheck {
        violations.assertNotPresent(message, path)
        return this
    }
}
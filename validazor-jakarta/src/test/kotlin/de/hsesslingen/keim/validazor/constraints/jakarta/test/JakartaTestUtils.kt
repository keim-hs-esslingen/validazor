package de.hsesslingen.keim.validazor.constraints.jakarta.test

import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.NowContext.Companion.fromSystemNow
import de.hsesslingen.keim.validazor.Validazor
import de.hsesslingen.keim.validazor.Violation
import de.hsesslingen.keim.validazor.constraints.jakarta.JakartaValidationModule
import de.hsesslingen.keim.validazor.test.assertAny
import de.hsesslingen.keim.validazor.test.assertNone

fun validazor(): Validazor {
    return Validazor.Builder()
        .register(JakartaValidationModule())
        .build()
}

fun validate(obj: Any, nowContext: NowContext = fromSystemNow()): List<Violation> {
    return validazor().validate(obj, nowContext)
}

fun assertValid(obj: Any, nowContext: NowContext = fromSystemNow()) {
    validazor().assertValid(obj, nowContext)
}

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
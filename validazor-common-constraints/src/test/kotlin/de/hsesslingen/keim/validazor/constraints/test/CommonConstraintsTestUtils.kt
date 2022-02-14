package de.hsesslingen.keim.validazor.constraints.test

import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.NowContext.Companion.fromSystemNow
import de.hsesslingen.keim.validazor.Validazor
import de.hsesslingen.keim.validazor.Violation
import de.hsesslingen.keim.validazor.constraints.registerCommonConstraints
import de.hsesslingen.keim.validazor.testutils.assertAny
import de.hsesslingen.keim.validazor.testutils.assertNone

fun validazor(): Validazor {
    return Validazor.Builder()
        .registerCommonConstraints()
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

fun <C : Collection<Violation>> C.assertProperty(path: String): PathCheck {
    return PathCheck(this, path)
}

class PathCheck(
    private val violations: Collection<Violation>,
    private val path: String
) {
    fun hasMessage(message: String): PathCheck {
        violations.assertPresent(message, path)
        return this
    }

    fun hasMessageStartingWith(messagePrefix: String): PathCheck {
        violations.any { it.path == path && it.message.startsWith(messagePrefix) }
        return this
    }

    fun hasMessageEndingWith(messageSuffix: String): PathCheck {
        violations.any { it.path == path && it.message.endsWith(messageSuffix) }
        return this
    }

    fun hasNotMessage(message: String): PathCheck {
        violations.assertNotPresent(message, path)
        return this
    }
}
package de.hsesslingen.keim.validazor.test

import de.hsesslingen.keim.validazor.Violation

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
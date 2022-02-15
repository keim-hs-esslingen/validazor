package de.hsesslingen.keim.validazor.test

import de.hsesslingen.keim.validazor.Violation

class PathCheck(
    private val violations: Collection<Violation>,
    private val path: String
) {
    fun hasMessage(message: String): PathCheck {
        violations.assertPresent(message, path)
        return this
    }

    fun hasMessage(messageTest: MessageTest): PathCheck {
        violations.assertPresent(messageTest, path)
        return this
    }

    fun hasNotMessage(message: String): PathCheck {
        violations.assertNotPresent(message, path)
        return this
    }

    fun hasNotMessage(messageTest: MessageTest): PathCheck {
        violations.assertNotPresent(messageTest, path)
        return this
    }
}
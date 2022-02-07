package de.hsesslingen.keim.validazor.testutil

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector

annotation class OlderThanFriends {
    class Validator : ConstraintValidazor<OlderThanFriends> {
        override fun validate(
            constraint: OlderThanFriends,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean
        ) {
            when (value) {
                null -> {}
                is Person -> {
                    if (value.age < (value.friends?.maxOfOrNull { it.age } ?: 0)) {
                        violations.add(MSG, path, constraint)
                    }
                }
            }
        }
    }

    companion object {
        const val MSG = "must be older than all friends"
    }
}

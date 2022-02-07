package de.hsesslingen.keim.validazor.testutil

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import de.hsesslingen.keim.validazor.toConstraintInfo

annotation class NotNull {
    class Validator : ConstraintValidazor<NotNull> {
        override fun validate(
            constraint: NotNull,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean
        ) {
            if (value == null) {
                violations.add(MSG, path, constraint.toConstraintInfo())
            }
        }
    }

    companion object {
        const val MSG = "must not be null"
    }
}
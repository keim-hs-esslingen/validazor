package de.hsesslingen.keim.validazor.test

import de.hsesslingen.keim.validazor.*

annotation class NotNull {
    class Validator : ConstraintValidazor<NotNull> {
        override fun validate(
            constraint: NotNull,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
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
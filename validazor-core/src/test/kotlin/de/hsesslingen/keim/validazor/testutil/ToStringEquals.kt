package de.hsesslingen.keim.validazor.testutil

import de.hsesslingen.keim.validazor.*

/**
 * Test constraint for unit tests.
 */
annotation class ToStringEquals(
    val requiredValue: String
) {
    class Validator : ConstraintValidazor<ToStringEquals> {
        override fun validate(
            constraint: ToStringEquals,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            when (value) {
                null -> {}
                else -> {
                    if (value.toString() != constraint.requiredValue) {
                        violations.add(
                            makeMessage(constraint.requiredValue),
                            path,
                            constraint.toConstraintInfo()
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val MSG = "stringified value must equal"

        fun makeMessage(requiredValue: String): String {
            return "$MSG \"$requiredValue\""
        }
    }
}

package de.hsesslingen.keim.validazor.testutil

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import de.hsesslingen.keim.validazor.toConstraintInfo

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
            returnOnFirstViolation: Boolean
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
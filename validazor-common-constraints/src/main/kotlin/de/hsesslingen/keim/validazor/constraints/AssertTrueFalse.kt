package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector

sealed class BooleanValidator<A : Annotation>(
    private val requiredValue: Boolean
) : ConstraintValidator<A> {
    override fun validate(
        constraint: A,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must be $requiredValue", path, constraint, violations) {
            when (value) {
                is Boolean -> value == requiredValue
                else -> true
            }
        }
    }
}

/**
 * Validates that a boolean value is `true`.
 *
 * Supported types:
 * - [Boolean]
 *
 * `null` is considered valid.
 */
annotation class AssertTrue {
    /**
     * [ConstraintValidator] for the [AssertTrue] constraint.
     */
    class Validator : BooleanValidator<AssertTrue>(true)
}

/**
 * Validates that a boolean value is `false`.
 *
 * Supported types:
 * - [Boolean]
 *
 * `null` is considered valid.
 */
annotation class AssertFalse {
    /**
     * [ConstraintValidator] for the [AssertFalse] constraint.
     */
    class Validator : BooleanValidator<AssertFalse>(false)
}


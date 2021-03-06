package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.AssertFalse
import jakarta.validation.constraints.AssertTrue

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
 * A [ConstraintValidator] for the Jakarta [AssertFalse] constraint.
 */
class AssertFalseValidator : BooleanValidator<AssertFalse>(false)

/**
 * A [ConstraintValidator] for the Jakarta [AssertTrue] constraint.
 */
class AssertTrueValidator : BooleanValidator<AssertTrue>(true)
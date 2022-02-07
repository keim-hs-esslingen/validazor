package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.AssertFalse
import jakarta.validation.constraints.AssertTrue

sealed class BooleanValidazor<A : Annotation>(
    private val requiredValue: Boolean
) : ConstraintValidazor<A> {
    override fun validate(
        constraint: A,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
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
 * A [ConstraintValidazor] for the Jakarta [AssertFalse] constraint.
 */
class AssertFalseValidazor : BooleanValidazor<AssertFalse>(false)

/**
 * A [ConstraintValidazor] for the Jakarta [AssertTrue] constraint.
 */
class AssertTrueValidazor : BooleanValidazor<AssertTrue>(true)
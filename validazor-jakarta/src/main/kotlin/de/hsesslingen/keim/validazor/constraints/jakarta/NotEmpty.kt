package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.NotEmpty

/**
 * A [ConstraintValidazor] for the Jakarta [NotEmpty] constraint.
 */
class NotEmptyValidazor : ConstraintValidazor<NotEmpty> {
    override fun validate(
        constraint: NotEmpty,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
    ) {
        checkConstraint("must not be empty", path, constraint, violations) {
            when (value) {
                is String -> value.isNotEmpty()
                is Collection<*> -> value.isNotEmpty()
                is Array<*> -> value.isNotEmpty()
                else -> true
            }
        }
    }
}
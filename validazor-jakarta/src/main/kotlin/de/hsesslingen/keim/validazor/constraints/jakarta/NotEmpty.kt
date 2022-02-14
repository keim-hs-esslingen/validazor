package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.NotEmpty

/**
 * A [ConstraintValidator] for the Jakarta [NotEmpty] constraint.
 */
class NotEmptyValidator : ConstraintValidator<NotEmpty> {
    override fun validate(
        constraint: NotEmpty,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must not be empty", path, constraint, violations) {
            when (value) {
                is CharSequence -> value.isNotEmpty()
                is Collection<*> -> value.isNotEmpty()
                is Map<*, *> -> value.isNotEmpty()
                is Array<*> -> value.isNotEmpty()
                else -> true
            }
        }
    }
}
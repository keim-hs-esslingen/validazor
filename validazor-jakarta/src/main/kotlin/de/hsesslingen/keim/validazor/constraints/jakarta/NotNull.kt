package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.NotNull

/**
 * A [ConstraintValidator] for the Jakarta [NotNull] constraint.
 */
class NotNullValidator : ConstraintValidator<NotNull> {
    override fun validate(
        constraint: NotNull,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must not be null", path, constraint, violations) {
            value != null
        }
    }
}
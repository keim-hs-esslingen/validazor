package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.NotNull

/**
 * A [ConstraintValidazor] for the Jakarta [NotNull] constraint.
 */
class NotNullValidazor : ConstraintValidazor<NotNull> {
    override fun validate(
        constraint: NotNull,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
    ) {
        checkConstraint("must not be null", path, constraint, violations) {
            value != null
        }
    }
}
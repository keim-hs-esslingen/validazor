package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.NotBlank

/**
 * A [ConstraintValidazor] for the Jakarta [NotBlank] constraint.
 */
class NotBlankValidazor : ConstraintValidazor<NotBlank> {
    override fun validate(
        constraint: NotBlank,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
    ) {
        checkConstraint("must not be blank", path, constraint, violations) {
            value !is String || value.isNotBlank()
        }
    }
}
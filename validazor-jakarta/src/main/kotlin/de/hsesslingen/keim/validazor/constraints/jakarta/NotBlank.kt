package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.NotBlank

/**
 * A [ConstraintValidator] for the Jakarta [NotBlank] constraint.
 */
class NotBlankValidator : ConstraintValidator<NotBlank> {
    override fun validate(
        constraint: NotBlank,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must not be blank", path, constraint, violations) {
            value !is CharSequence || value.isNotBlank()
        }
    }
}
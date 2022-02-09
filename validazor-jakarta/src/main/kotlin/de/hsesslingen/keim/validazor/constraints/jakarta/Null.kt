package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Null

/**
 * A [ConstraintValidazor] for the Jakarta [Null] constraint.
 */
class NullValidazor : ConstraintValidazor<Null> {
    override fun validate(
        constraint: Null,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must be null", path, constraint, violations) {
            value == null
        }
    }
}
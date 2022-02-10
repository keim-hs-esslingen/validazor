package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Negative
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A [ConstraintValidator] for the Jakarta [Negative] constraint.
 */
class NegativeValidator : ConstraintValidator<Negative> {
    override fun validate(
        constraint: Negative,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must be less than zero", path, constraint, violations) {
            when (value) {
                is Int -> value < 0
                is Long -> value < 0
                is Short -> value < 0
                is Byte -> value < 0
                is Double -> value.isNaN() || value < 0
                is Float -> value.isNaN() || value < 0
                is BigDecimal -> value.signum() == -1
                is BigInteger -> value.signum() == -1
                else -> true
            }
        }
    }
}
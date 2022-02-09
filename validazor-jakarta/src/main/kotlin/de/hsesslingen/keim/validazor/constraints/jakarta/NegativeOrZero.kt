package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.NegativeOrZero
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A [ConstraintValidazor] for the Jakarta [NegativeOrZero] constraint.
 */
class NegativeOrZeroValidazor : ConstraintValidazor<NegativeOrZero> {
    override fun validate(
        constraint: NegativeOrZero,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint(
            "must be less than or equal to zero",
            path,
            constraint,
            violations
        ) {
            when (value) {
                is Int -> value <= 0
                is Long -> value <= 0
                is Short -> value <= 0
                is Byte -> value <= 0
                is Double -> value.isNaN() || value <= 0
                is Float -> value.isNaN() || value <= 0
                is BigDecimal -> value.signum() != 1
                is BigInteger -> value.signum() != 1
                else -> true
            }
        }
    }
}
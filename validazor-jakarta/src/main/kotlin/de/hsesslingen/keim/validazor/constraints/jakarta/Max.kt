package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Max
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A [ConstraintValidator] for the Jakarta [Max] constraint.
 */
class MaxValidator : ConstraintValidator<Max> {
    override fun validate(
        constraint: Max,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        val maxValue = constraint.value

        checkConstraint("must be less than $maxValue", path, constraint, violations) {
            when (value) {
                is Int -> value <= maxValue
                is Long -> value <= maxValue
                is Short -> value <= maxValue
                is Byte -> value <= maxValue
                is Double -> value <= maxValue
                is Float -> value <= maxValue
                is BigDecimal -> value <= BigDecimal.valueOf(maxValue)
                is BigInteger -> value <= BigInteger.valueOf(maxValue)
                else -> true
            }
        }
    }
}
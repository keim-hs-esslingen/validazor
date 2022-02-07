package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Max
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A [ConstraintValidazor] for the Jakarta [Max] constraint.
 */
class MaxValidazor : ConstraintValidazor<Max> {
    override fun validate(
        constraint: Max,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
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
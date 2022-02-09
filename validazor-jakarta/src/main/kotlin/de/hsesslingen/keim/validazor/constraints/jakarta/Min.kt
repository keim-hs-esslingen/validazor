package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Min
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A [ConstraintValidazor] for the Jakarta [Min] constraint.
 */
class MinValidazor : ConstraintValidazor<Min> {
    override fun validate(
        constraint: Min,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        val minValue = constraint.value

        checkConstraint(
            "must be greater than $minValue",
            path,
            constraint,
            violations
        ) {
            when (value) {
                is Int -> value >= minValue
                is Long -> value >= minValue
                is Short -> value >= minValue
                is Byte -> value >= minValue
                is Double -> value >= minValue
                is Float -> value >= minValue
                is BigDecimal -> value >= BigDecimal.valueOf(minValue)
                is BigInteger -> value >= BigInteger.valueOf(minValue)
                else -> true
            }
        }
    }
}
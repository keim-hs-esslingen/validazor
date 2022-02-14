package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import java.math.BigDecimal
import java.math.BigInteger

/**
 * Validates whether the annotated field value is positive.
 *
 * Supported types:
 * - [Int]
 * - [Long]
 * - [Short]
 * - [Byte]
 * - [Double]
 * - [Float]
 * - [BigInteger]
 * - [BigDecimal]
 *
 * `null` is considered value.
 */
annotation class Positive {
    /**
     * [ConstraintValidator] for the [Positive] constraint.
     */
    class Validator : ConstraintValidator<Positive> {
        override fun validate(
            constraint: Positive,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            checkConstraint("must be greater than zero", path, constraint, violations) {
                when (value) {
                    is Int -> value > 0
                    is Long -> value > 0
                    is Short -> value > 0
                    is Byte -> value > 0
                    is Double -> value.isNaN() || value > 0
                    is Float -> value.isNaN() || value > 0
                    is BigDecimal -> value.signum() == 1
                    is BigInteger -> value.signum() == 1
                    else -> true
                }
            }
        }
    }
}
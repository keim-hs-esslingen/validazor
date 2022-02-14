package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * Validates whether the annotated field value is negative.
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
@Target(FIELD, CLASS)
annotation class Negative {

    /**
     * [ConstraintValidator] for the [Negative] constraint.
     */
    class Validator : ConstraintValidator<Negative> {
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
}
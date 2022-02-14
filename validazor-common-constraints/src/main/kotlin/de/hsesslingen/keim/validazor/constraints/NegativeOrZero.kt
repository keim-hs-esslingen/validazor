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
 * Validates whether the annotated field value is negative or zero.
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
annotation class NegativeOrZero {

    /**
     * [ConstraintValidator] for the [NegativeOrZero] constraint.
     */
    class Validator : ConstraintValidator<NegativeOrZero> {
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
}
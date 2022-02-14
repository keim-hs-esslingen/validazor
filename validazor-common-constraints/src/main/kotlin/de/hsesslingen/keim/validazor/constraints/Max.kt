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
 * Validates that a number is less than or equal to [value].
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
 * `null` is considered valid.
 */
@Target(FIELD, CLASS)
annotation class Max(
    /**
     * The maximum value that the annotated field value may not exceed.
     */
    val value: Long
) {

    /**
     * [ConstraintValidator] for the [Max] constraint.
     */
    class Validator : ConstraintValidator<Max> {
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
}
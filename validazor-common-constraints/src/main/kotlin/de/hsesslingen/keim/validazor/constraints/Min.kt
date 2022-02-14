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
 * Validates that a number is greater than or equal to [value].
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
annotation class Min(
    /**
     * The minimum value that the annotated field value may not undermatch.
     */
    val value: Long
) {

    /**
     * [ConstraintValidator] for the [Min] constraint.
     */
    class Validator : ConstraintValidator<Min> {
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
}
package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import java.math.BigDecimal
import java.math.BigInteger

/**
 * Validates that a number is greater than (or equal to, if [inclusive]` == true`) [value].
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
annotation class DecimalMin(
    /**
     * The minimum value that the annotated field value may not undermatch.
     * The given string must be parsable to the supported type that this annotation is used on.
     */
    val value: String,
    /**
     * Whether the annotated field value may be *equal to* [value], or not.
     */
    val inclusive: Boolean = true,
) {
    /**
     * [ConstraintValidator] for the [DecimalMin] constraint.
     */
    class Validator : ConstraintValidator<DecimalMin> {
        override fun validate(
            constraint: DecimalMin,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            if (constraint.inclusive) {
                checkConstraint(
                    "must be greater than or equal to ${constraint.value}",
                    path,
                    constraint,
                    violations
                ) {
                    when (value) {
                        is Int -> value >= constraint.value.toInt()
                        is Long -> value >= constraint.value.toLong()
                        is Short -> value >= constraint.value.toShort()
                        is Byte -> value >= constraint.value.toByte()
                        is Double -> value >= constraint.value.toDouble()
                        is Float -> value >= constraint.value.toFloat()
                        is BigDecimal -> value >= constraint.value.toBigDecimal()
                        is BigInteger -> value >= constraint.value.toBigInteger()
                        else -> true
                    }
                }
            } else {
                checkConstraint(
                    "must be greater than ${constraint.value}",
                    path,
                    constraint,
                    violations
                ) {
                    when (value) {
                        is Int -> value > constraint.value.toInt()
                        is Long -> value > constraint.value.toLong()
                        is Short -> value > constraint.value.toShort()
                        is Byte -> value > constraint.value.toByte()
                        is Double -> value > constraint.value.toDouble()
                        is Float -> value > constraint.value.toFloat()
                        is BigDecimal -> value > constraint.value.toBigDecimal()
                        is BigInteger -> value > constraint.value.toBigInteger()
                        else -> true
                    }
                }
            }
        }
    }
}
package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import java.math.BigDecimal
import java.math.BigInteger

/**
 * Validates that a number is less than (or equal to, if [inclusive]` == true`) [value].
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
 * - [CharSequence] (includes [String])
 *
 * `null` is considered valid.
 */
annotation class DecimalMax(
    /**
     * The maximum value that the annotated field value may not exceed.
     * The given string must be parsable to the supported type that this annotation is used on.
     */
    val value: String,
    /**
     * Whether the annotated field value may be *equal to* [value], or not.
     */
    val inclusive: Boolean = true,
) {

    /**
     * [ConstraintValidator] for the [DecimalMax] constraint.
     */
    class Validator : ConstraintValidator<DecimalMax> {
        override fun validate(
            constraint: DecimalMax,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            if (constraint.inclusive) {
                checkConstraint(
                    "must be less than or equal to ${constraint.value}",
                    path,
                    constraint,
                    violations
                ) {
                    when (value) {
                        is Int -> value <= constraint.value.toInt()
                        is Long -> value <= constraint.value.toLong()
                        is Short -> value <= constraint.value.toShort()
                        is Byte -> value <= constraint.value.toByte()
                        is Double -> value <= constraint.value.toDouble()
                        is Float -> value <= constraint.value.toFloat()
                        is BigDecimal -> value <= constraint.value.toBigDecimal()
                        is BigInteger -> value <= constraint.value.toBigInteger()
                        is String -> BigDecimal(value) <= constraint.value.toBigDecimal()
                        is CharSequence -> BigDecimal(value.toString()) <= constraint.value.toBigDecimal()
                        else -> true
                    }
                }
            } else {
                checkConstraint(
                    "must be less than ${constraint.value}",
                    path,
                    constraint,
                    violations
                ) {
                    when (value) {
                        is Int -> value < constraint.value.toInt()
                        is Long -> value < constraint.value.toLong()
                        is Short -> value < constraint.value.toShort()
                        is Byte -> value < constraint.value.toByte()
                        is Double -> value < constraint.value.toDouble()
                        is Float -> value < constraint.value.toFloat()
                        is BigDecimal -> value < constraint.value.toBigDecimal()
                        is BigInteger -> value < constraint.value.toBigInteger()
                        is String -> BigDecimal(value) < constraint.value.toBigDecimal()
                        is CharSequence -> BigDecimal(value.toString()) < constraint.value.toBigDecimal()
                        else -> true
                    }
                }
            }
        }
    }
}
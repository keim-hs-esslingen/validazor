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
 * The annotated field value must have less than or equally many integer and fractional digits.
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
 * `null` values are considered valid.
 */
@Target(FIELD, CLASS)
annotation class Digits(
    /**
     * The maximum number of integer digits the annotated field value may have.
     */
    val integer: Int,
    /**
     * The maximum number of fractional digits the annotated field value may have. Ignored on integer number types.
     */
    val fraction: Int,
) {

    /**
     * [ConstraintValidator] for the [Digits] constraint.
     */
    class Validator : ConstraintValidator<Digits> {

        private fun BigDecimal.matchesDigitConstraints(digits: Digits): Boolean {
            return this.matchesDigitConstraints(digits.integer, digits.fraction)
        }

        override fun validate(
            constraint: Digits,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            checkConstraint(
                "must have at most ${constraint.integer} integer digits and ${constraint.fraction} fractional digits",
                path,
                constraint, violations
            ) {
                when (value) {
                    null -> true

                    is Byte -> value.toLong().toBigDecimal().matchesDigitConstraints(constraint)
                    is Short -> value.toLong().toBigDecimal().matchesDigitConstraints(constraint)
                    is Int -> value.toBigDecimal().matchesDigitConstraints(constraint)
                    is Long -> value.toBigDecimal().matchesDigitConstraints(constraint)

                    is Float -> value.toBigDecimal().matchesDigitConstraints(constraint)
                    is Double -> value.toBigDecimal().matchesDigitConstraints(constraint)
                    is BigInteger -> value.toBigDecimal().matchesDigitConstraints(constraint)
                    is BigDecimal -> value.matchesDigitConstraints(constraint)

                    is String -> value.toBigDecimal().matchesDigitConstraints(constraint)
                    is CharSequence -> value.toString().toBigDecimal().matchesDigitConstraints(constraint)

                    else -> true
                }
            }
        }
    }
}
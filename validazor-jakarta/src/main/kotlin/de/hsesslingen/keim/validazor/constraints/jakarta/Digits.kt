package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Digits
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A [ConstraintValidazor] for the Jakarta [Digits] constraint.
 */
class DigitsValidazor : ConstraintValidazor<Digits> {

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
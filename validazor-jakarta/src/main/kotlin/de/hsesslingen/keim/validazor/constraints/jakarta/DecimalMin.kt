package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.DecimalMin
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A [ConstraintValidazor] for the Jakarta [DecimalMin] constraint.
 */
class DecimalMinValidazor : ConstraintValidazor<DecimalMin> {
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
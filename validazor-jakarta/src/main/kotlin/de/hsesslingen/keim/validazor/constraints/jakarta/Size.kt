package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Size

/**
 * A [ConstraintValidazor] for the Jakarta [Size] constraint.
 */
class SizeValidazor : ConstraintValidazor<Size> {
    override fun validate(
        constraint: Size,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
    ) {
        val min = constraint.min
        val max = constraint.max

        checkConstraint(
            "must be of size min $min and max $max",
            path,
            constraint,
            violations
        ) {
            when (value) {
                is String -> value.length in min..max
                is Collection<*> -> value.size in min..max
                is Array<*> -> value.size in min..max
                else -> true
            }
        }
    }
}
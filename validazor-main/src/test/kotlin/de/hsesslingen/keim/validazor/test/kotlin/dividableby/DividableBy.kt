package de.hsesslingen.keim.validazor.test.kotlin.dividableby

import de.hsesslingen.keim.validazor.*
import de.hsesslingen.keim.validazor.test.java.dividableby.DividableBy

annotation class DividableBy(
    val value: Long = 1
) {
    class Validator : ConstraintValidator<DividableBy> {
        override fun validate(
            // The particular instance of your constraint.
            constraint: DividableBy,
            // The value that must be tested.
            value: Any?,
            // The path to the object. Only needed for adding violations.
            path: PropertyPath,
            // A simple collector for violations.
            violations: ViolationCollector,
            // Whether to return after finding a first violation.
            // Only needed if multiple violations can be found with one constraint.
            returnOnFirstViolation: Boolean,
            // A reference for the current moment in time for validation, provided in various types.
            now: NowContext
        ) {
            // Calculate remainder on known types.
            val remainder = when (value) {
                null -> return // null values usually are considered valid. Nothing to do.
                is Int -> value % constraint.value
                is Long -> value % constraint.value
                else -> return // Other types not supported. Nothing to do.
            }

            // Test if remainder is not zero.
            if (remainder != 0L) {
                // Upon violations, add a violation using a descriptive message,
                // the path and a constraint info object, either self-made or
                // generated using the handy extension function for Kotlin users.
                violations.add(
                    "must be dividable by " + constraint.value,
                    path,
                    constraint.toConstraintInfo()
                )
            }
        }
    }
}

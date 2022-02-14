package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * Validates whether the annotated field value is `null`.
 *
 * Supported types: any
 */
@Target(FIELD, CLASS)
annotation class Null {

    /**
     * [ConstraintValidator] for the [Null] constraint.
     */
    class Validator : ConstraintValidator<Null> {
        override fun validate(
            constraint: Null,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            checkConstraint("must be null", path, constraint, violations) {
                value == null
            }
        }
    }
}
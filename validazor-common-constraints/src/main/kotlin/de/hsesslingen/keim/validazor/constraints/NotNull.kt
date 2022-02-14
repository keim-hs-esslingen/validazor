package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * Validates whether the annotated field value is not `null`.
 *
 * Supported types: any
 */
@Target(FIELD, CLASS)
annotation class NotNull {

    /**
     * [ConstraintValidator] for the [NotNull] constraint.
     */
    class Validator : ConstraintValidator<NotNull> {
        override fun validate(
            constraint: NotNull,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            checkConstraint("must not be null", path, constraint, violations) {
                value != null
            }
        }
    }
}
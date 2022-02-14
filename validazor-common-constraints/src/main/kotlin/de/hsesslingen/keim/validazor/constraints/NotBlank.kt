package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * Validates whether the annotated field value it not blank,
 * i.e. is not empty and not composed of only whitespace characters.
 *
 * Supported types:
 * - [CharSequence] (includes [String])
 *
 * `null` is considered valid.
 */
@Target(FIELD, CLASS)
annotation class NotBlank {

    /**
     * [ConstraintValidator] for the [NotBlank] constraint.
     */
    class Validator : ConstraintValidator<NotBlank> {
        override fun validate(
            constraint: NotBlank,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            checkConstraint("must not be blank", path, constraint, violations) {
                value !is CharSequence || value.isNotBlank()
            }
        }
    }
}
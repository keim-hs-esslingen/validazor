package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * Validates whether the annotated field value it not empty.
 *
 * Supported types:
 * - [CharSequence] (includes [String])
 * - [Collection]
 * - [Map]
 * - [Array]
 *
 * `null` is considered valid.
 */
@Target(FIELD, CLASS)
annotation class NotEmpty {

    /**
     * [ConstraintValidator] for the [NotEmpty] constraint.
     */
    class Validator : ConstraintValidator<NotEmpty> {
        override fun validate(
            constraint: NotEmpty,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            checkConstraint("must not be empty", path, constraint, violations) {
                when (value) {
                    is CharSequence -> value.isNotEmpty()
                    is Collection<*> -> value.isNotEmpty()
                    is Map<*, *> -> value.isNotEmpty()
                    is Array<*> -> value.isNotEmpty()
                    else -> true
                }
            }
        }
    }
}
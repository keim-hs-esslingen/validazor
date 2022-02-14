package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * Validates whether the size of the annotated field value does not exceed [max] and undermatch [min].
 *
 * Supported types:
 * - [CharSequence] (includes [String]), interpreted as length.
 * - [Collection]
 * - [Map]
 * - [Array]
 *
 * `null` is considered valid.
 */
@Target(FIELD, CLASS)
annotation class Size(
    /**
     * The minimum size that the annotated field value must have.
     */
    val min: Int = 0,
    /**
     * The maximum size that the annotated field value may have.
     */
    val max: Int = Integer.MAX_VALUE,
) {

    /**
     * [ConstraintValidator] for the [Size] constraint.
     */
    class Validator : ConstraintValidator<Size> {
        override fun validate(
            constraint: Size,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
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
                    is CharSequence -> value.length in min..max
                    is Collection<*> -> value.size in min..max
                    is Map<*, *> -> value.size in min..max
                    is Array<*> -> value.size in min..max
                    else -> true
                }
            }
        }
    }
}
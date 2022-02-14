package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * Validates whether the annotated field value matches the [regexp] pattern.
 *
 * Supported types:
 * - [CharSequence] (includes [String])
 *
 * `null` is considered valid.
 */
@Target(FIELD, CLASS)
annotation class Pattern(
    /**
     * The regular expression that the annotated field value must match.
     *
     * The used regex implementation is [java.util.regex.Pattern].
     */
    val regexp: String,
    /**
     * Optional expression flags for [regexp].
     */
    val flags: IntArray = [],
) {

    /**
     * [ConstraintValidator] for the [Pattern] constraint.
     */
    class Validator : ConstraintValidator<Pattern> {
        override fun validate(
            constraint: Pattern,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            checkConstraint("must match pattern \"${constraint.regexp}\"", path, constraint, violations) {
                value !is CharSequence || java.util.regex.Pattern.compile(
                    constraint.regexp,
                    constraint.flags.mergeWithOr()
                )
                    .matcher(value).matches()
            }
        }
    }
}
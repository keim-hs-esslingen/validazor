package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import java.util.regex.Pattern

/**
 * Validates whether the annotated field value represents a valid email address and
 * optionally whether it matches a given regular expression.
 *
 * Supported types:
 * - [CharSequence] (includes [String])
 *
 * `null` values are considered valid.
 */
annotation class Email(
    /**
     * An optional, additional regular expression that the annotated field value must match.
     * The used regex implementation is [java.util.regex.Pattern].
     */
    val regexp: String = ".*",
    /**
     * Optional expression flags for [regexp].
     */
    val flags: IntArray = [],
) {

    /**
     * A [ConstraintValidator] for the Jakarta [Email] constraint.
     */
    class EmailValidator : ConstraintValidator<Email> {
        override fun validate(
            constraint: Email,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext
        ) {
            if (constraint.regexp != ".*") {
                // Include extra pattern defined in this Email annotation instance.
                val extraPattern = Pattern.compile(constraint.regexp, constraint.flags.mergeWithOr())
                val violationMessage =
                    "must be a valid email address and additionally match pattern \"${constraint.regexp}\""

                checkConstraint(violationMessage, path, constraint, violations) {
                    value !is CharSequence || (VALID_EMAIL_PATTERN.matches(value) && extraPattern.matcher(value)
                        .matches())
                }
            } else {
                checkConstraint("must be a valid email address", path, constraint, violations) {
                    value !is CharSequence || VALID_EMAIL_PATTERN.matches(value)
                }
            }
        }
    }
}
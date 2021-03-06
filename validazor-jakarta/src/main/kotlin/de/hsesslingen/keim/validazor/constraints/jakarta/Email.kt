package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Email

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
            val extraPattern = java.util.regex.Pattern.compile(constraint.regexp, constraint.flags.merge())
            val violationMessage = "must be a valid email address and additionally match pattern \"${constraint.regexp}\""

            checkConstraint(violationMessage, path, constraint, violations) {
                value !is CharSequence || (VALID_EMAIL_PATTERN.matches(value) && extraPattern.matcher(value).matches())
            }
        } else {
            checkConstraint("must be a valid email address", path, constraint, violations) {
                value !is CharSequence || VALID_EMAIL_PATTERN.matches(value)
            }
        }
    }
}
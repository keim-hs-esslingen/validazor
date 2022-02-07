package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Email

/**
 * A [ConstraintValidazor] for the Jakarta [Email] constraint.
 */
class EmailValidazor : ConstraintValidazor<Email> {
    override fun validate(
        constraint: Email,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
    ) {
        if (constraint.regexp != ".*") {
            // Include extra pattern defined in this Email annotation instance.
            val extraPattern = java.util.regex.Pattern.compile(constraint.regexp, constraint.flags.xord())
            val violationMessage = "must be a valid email address and additionally match pattern ${constraint.regexp}"

            checkConstraint(violationMessage, path, constraint, violations) {
                value !is String || (VALID_EMAIL_PATTERN.matches(value) && extraPattern.matcher(value).matches())
            }
        } else {
            checkConstraint("must be a valid email address", path, constraint, violations) {
                value !is String || VALID_EMAIL_PATTERN.matches(value)
            }
        }
    }
}
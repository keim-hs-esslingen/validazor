package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Pattern

/**
 * A [ConstraintValidator] for the Jakarta [Pattern] constraint.
 */
class PatternValidator : ConstraintValidator<Pattern> {
    override fun validate(
        constraint: Pattern,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must match pattern \"${constraint.regexp}\"", path, constraint, violations) {
            value !is String || java.util.regex.Pattern.compile(constraint.regexp, constraint.flags.merge())
                .matcher(value).matches()
        }
    }
}
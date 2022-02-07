package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Pattern

/**
 * A [ConstraintValidazor] for the Jakarta [Pattern] constraint.
 */
class PatternValidazor : ConstraintValidazor<Pattern> {
    override fun validate(
        constraint: Pattern,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
    ) {
        checkConstraint("must match pattern", path, constraint, violations) {
            value !is String || java.util.regex.Pattern.compile(constraint.regexp, constraint.flags.xord())
                .matcher(value).matches()
        }
    }
}
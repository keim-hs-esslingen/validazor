package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Future
import java.time.*
import java.util.*

/**
 * A [ConstraintValidazor] for the Jakarta [Future] constraint.
 */
class FutureValidazor : ConstraintValidazor<Future> {
    override fun validate(
        constraint: Future,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
    ) {
        checkConstraint("must be in future", path, constraint, violations) {
            when (value) {
                is Instant -> value.isAfter(Instant.now())
                is LocalTime -> value.isAfter(LocalTime.now())
                is LocalDateTime -> value.isAfter(LocalDateTime.now())
                is LocalDate -> value.isAfter(LocalDate.now())
                is OffsetDateTime -> value.isAfter(OffsetDateTime.now())
                is OffsetTime -> value.isAfter(OffsetTime.now())
                is ZonedDateTime -> value.isAfter(ZonedDateTime.now())
                is Year -> value.isAfter(Year.now())
                is YearMonth -> value.isAfter(YearMonth.now())
                is MonthDay -> value.isAfter(MonthDay.now())
                is Date -> value.time > Date().time
                is Calendar -> value.timeInMillis > Calendar.getInstance().timeInMillis
                else -> true
            }
        }
    }
}
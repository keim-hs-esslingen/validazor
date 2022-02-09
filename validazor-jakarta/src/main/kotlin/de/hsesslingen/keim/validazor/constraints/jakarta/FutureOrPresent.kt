package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.FutureOrPresent
import java.time.*
import java.util.*

/**
 * A [ConstraintValidazor] for the Jakarta [FutureOrPresent] constraint.
 */
class FutureOrPresentValidazor : ConstraintValidazor<FutureOrPresent> {
    override fun validate(
        constraint: FutureOrPresent,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must be in future or present", path, constraint, violations) {
            when (value) {
                is Instant -> !value.isBefore(now.asInstant)
                is LocalTime -> !value.isBefore(now.asLocalTime)
                is LocalDateTime -> !value.isBefore(now.asLocalDateTime)
                is LocalDate -> !value.isBefore(now.asLocalDate)
                is OffsetDateTime -> !value.isBefore(now.asOffsetDateTime)
                is OffsetTime -> !value.isBefore(now.asOffsetTime)
                is ZonedDateTime -> !value.isBefore(now.asZonedDateTime)
                is Year -> !value.isBefore(now.asYear)
                is YearMonth -> !value.isBefore(now.asYearMonth)
                is Month -> value.value >= now.asMonth.value
                is MonthDay -> !value.isBefore(now.asMonthDay)
                // Use converted values for Date and Calendar to account for differences in implementation to java.time.*
                is Date -> value.time >= now.asDate.time
                is Calendar -> value.timeInMillis >= now.asCalendar.timeInMillis
                else -> true
            }
        }
    }
}
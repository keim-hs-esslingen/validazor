package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Past
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*

/**
 * A [ConstraintValidazor] for the Jakarta [Past] constraint.
 */
class PastValidator : ConstraintValidazor<Past> {
    override fun validate(
        constraint: Past,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must be in past", path, constraint, violations) {
            when (value) {
                is Instant -> value.isBefore(now.asInstant)
                is LocalTime -> value.isBefore(now.asLocalTime)
                is LocalDateTime -> value.isBefore(now.asLocalDateTime)
                is LocalDate -> value.isBefore(now.asLocalDate)
                is OffsetDateTime -> value.isBefore(now.asOffsetDateTime)
                is OffsetTime -> value.isBefore(now.asOffsetTime)
                is ZonedDateTime -> value.isBefore(now.asZonedDateTime)
                is Year -> value.isBefore(now.asYear)
                is YearMonth -> value.isBefore(now.asYearMonth)
                is Month -> value.value < now.asMonth.value
                is MonthDay -> value.isBefore(now.asMonthDay)
                is HijrahDate -> value.isBefore(now.asHijrahDate)
                is MinguoDate -> value.isBefore(now.asMinguoDate)
                is JapaneseDate -> value.isBefore(now.asJapaneseDate)
                is ThaiBuddhistDate -> value.isBefore(now.asThaiBuddhistDate)
                // Use converted values for Date and Calendar to account for differences in implementation to java.time.*
                is Date -> value.time < now.asDate.time
                is Calendar -> value.timeInMillis < now.asCalendar.timeInMillis
                else -> true
            }
        }
    }
}
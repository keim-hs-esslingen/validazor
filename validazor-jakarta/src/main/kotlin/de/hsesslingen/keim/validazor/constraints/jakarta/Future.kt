package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.Future
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
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
        returnOnFirstViolation: Boolean,
        now: NowContext
    ) {
        checkConstraint("must be in future", path, constraint, violations) {
            when (value) {
                is Instant -> value.isAfter(now.asInstant)
                is LocalTime -> value.isAfter(now.asLocalTime)
                is LocalDateTime -> value.isAfter(now.asLocalDateTime)
                is LocalDate -> value.isAfter(now.asLocalDate)
                is OffsetDateTime -> value.isAfter(now.asOffsetDateTime)
                is OffsetTime -> value.isAfter(now.asOffsetTime)
                is ZonedDateTime -> value.isAfter(now.asZonedDateTime)
                is Year -> value.isAfter(now.asYear)
                is YearMonth -> value.isAfter(now.asYearMonth)
                is Month -> value.value > now.asMonth.value
                is MonthDay -> value.isAfter(now.asMonthDay)
                is HijrahDate -> value.isAfter(now.asHijrahDate)
                is MinguoDate -> value.isAfter(now.asMinguoDate)
                is JapaneseDate -> value.isAfter(now.asJapaneseDate)
                is ThaiBuddhistDate -> value.isAfter(now.asThaiBuddhistDate)
                // Use converted values for Date and Calendar to account for differences in implementation to java.time.*
                is Date -> value.time > now.asDate.time
                is Calendar -> value.timeInMillis > now.asCalendar.timeInMillis
                else -> true
            }
        }
    }
}
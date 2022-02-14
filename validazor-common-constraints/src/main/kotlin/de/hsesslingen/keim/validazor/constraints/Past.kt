package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.*
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * Validates whether the annotated field value is in the past.
 *
 * For comparison, the [NowContext] of the validation is used, which can be specialized when calling
 * [Validazor.validate].
 *
 * Supported types:
 * - [Instant]
 * - [LocalTime]
 * - [LocalDate]
 * - [LocalDateTime]
 * - [OffsetTime]
 * - [OffsetDateTime]
 * - [ZonedDateTime]
 * - [Year]
 * - [YearMonth]
 * - [Month]
 * - [MonthDay]
 * - [HijrahDate]
 * - [MinguoDate]
 * - [JapaneseDate]
 * - [ThaiBuddhistDate]
 * - [Long] (interpreted as epoch millis)
 * - [Date]
 * - [Calendar]
 *
 * `null` is considered valid.
 */
@Target(FIELD, CLASS)
annotation class Past {

    /**
     * [ConstraintValidator] for the [Past] constraint.
     */
    class Validator : ConstraintValidator<Past> {
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
                    is Long -> value < now.asEpochMilli
                    // Use converted values for Date and Calendar to account for differences in implementation to java.time.*
                    is Date -> value.time < now.asDate.time
                    is Calendar -> value.timeInMillis < now.asCalendar.timeInMillis
                    else -> true
                }
            }
        }
    }
}
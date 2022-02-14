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
 * Validates whether the annotated field value is in the future.
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
annotation class Future {

    /**
     * [ConstraintValidator] for the [Future] constraint.
     */
    class Validator : ConstraintValidator<Future> {
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
                    is Long -> value > now.asEpochMilli
                    // Use converted values for Date and Calendar to account for differences in implementation to java.time.*
                    is Date -> value.time > now.asDate.time
                    is Calendar -> value.timeInMillis > now.asCalendar.timeInMillis
                    else -> true
                }
            }
        }
    }
}
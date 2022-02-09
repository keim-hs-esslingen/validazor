package de.hsesslingen.keim.validazor

import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*

private val THREAD_MODE: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE

/**
 * This class holds a reference for "now" in various types. These can be used by [ConstraintValidazor]s
 * for validating values that must be compared against a reference of "now".
 *
 * The now value can be provided by the initiator of the validation process and should reference the same context
 * as the data that is being validated, for as accurate results as possible.
 *
 * In this default implementation the various now values are all derived from an initial instance of [ZonedDateTime],
 * and are lazily converted to the required type upon first request. Subsequent requests to the same type of
 * temporal will return cached values of previous requests.
 *
 * If you need an instance of [NowContext] that behaves differently than this default implementation, you can
 * derive a child class and override any getter method you like. If you reuse the same context for multiple validations
 * performed simultaneously by multiple threads, make sure your implementation is thread safe. This default
 * implementation uses lazy conversion with caching of the various temporal types and is therefore **NOT** thread safe.
 */
open class NowContext(
    val asZonedDateTime: ZonedDateTime,
) {
    val asZoneId: ZoneId by lazy(THREAD_MODE) {
        asZonedDateTime.zone
    }
    val asInstant: Instant by lazy(THREAD_MODE) {
        asZonedDateTime.toInstant()
    }
    val asOffsetDateTime: OffsetDateTime by lazy(THREAD_MODE) {
        asZonedDateTime.toOffsetDateTime()
    }
    val asOffsetTime: OffsetTime by lazy(THREAD_MODE) {
        asOffsetDateTime.toOffsetTime() // Direct conversion from zoned date time not possible
    }
    val asZoneOffset: ZoneOffset by lazy(THREAD_MODE) {
        asOffsetTime.offset
    }
    val asLocalDateTime: LocalDateTime by lazy(THREAD_MODE) {
        asZonedDateTime.toLocalDateTime()
    }
    val asLocalDate: LocalDate by lazy(THREAD_MODE) {
        asZonedDateTime.toLocalDate()
    }
    val asLocalTime: LocalTime by lazy(THREAD_MODE) {
        asZonedDateTime.toLocalTime()
    }
    val asYear: Year by lazy(THREAD_MODE) {
        Year.from(asZonedDateTime)
    }
    val asYearMonth: YearMonth by lazy(THREAD_MODE) {
        YearMonth.from(asZonedDateTime)
    }
    val asMonth: Month by lazy(THREAD_MODE) {
        Month.from(asZonedDateTime)
    }
    val asMonthDay: MonthDay by lazy(THREAD_MODE) {
        MonthDay.from(asZonedDateTime)
    }
    val asDate: Date by lazy(THREAD_MODE) {
        Date.from(asInstant)
    }
    val asCalendar: GregorianCalendar by lazy(THREAD_MODE) {
        GregorianCalendar.from(asZonedDateTime)
    }
    val asEpochMilli: Long by lazy(THREAD_MODE) {
        asInstant.toEpochMilli()
    }
    val asHijrahDate: HijrahDate by lazy {
        HijrahDate.from(asZonedDateTime)
    }
    val asMinguoDate: MinguoDate by lazy {
        MinguoDate.from(asZonedDateTime)
    }
    val asJapaneseDate: JapaneseDate by lazy {
        JapaneseDate.from(asZonedDateTime)
    }
    val asThaiBuddhistDate: ThaiBuddhistDate by lazy {
        ThaiBuddhistDate.from(asZonedDateTime)
    }

    companion object {
        /**
         * Creates an instant of [NowContext] from [ZonedDateTime.now].
         *
         * This might not be valid for all validation scenarios and can lead to false violation detections
         * if local time values from other time zones than this JVM are compared against the current moment in time.
         */
        fun fromZonedDateTimeNow(): NowContext {
            return NowContext(ZonedDateTime.now())
        }
    }
}
package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.Future
import de.hsesslingen.keim.validazor.constraints.FutureOrPresent
import de.hsesslingen.keim.validazor.constraints.Past
import de.hsesslingen.keim.validazor.constraints.PastOrPresent
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*


class TemporalData(
    @Future
    val futureDateValue: Date? = null,
    @FutureOrPresent
    val futureOrPresentDateValue: Date? = null,
    @Past
    val pastDateValue: Date? = null,
    @PastOrPresent
    val pastOrPresentDateValue: Date? = null,


    @Future
    val futureCalendarValue: Calendar? = null,
    @FutureOrPresent
    val futureOrPresentCalendarValue: Calendar? = null,
    @Past
    val pastCalendarValue: Calendar? = null,
    @PastOrPresent
    val pastOrPresentCalendarValue: Calendar? = null,


    @Future
    val futureInstantValue: Instant? = null,
    @FutureOrPresent
    val futureOrPresentInstantValue: Instant? = null,
    @Past
    val pastInstantValue: Instant? = null,
    @PastOrPresent
    val pastOrPresentInstantValue: Instant? = null,


    @Future
    val futureLocalDateValue: LocalDate? = null,
    @FutureOrPresent
    val futureOrPresentLocalDateValue: LocalDate? = null,
    @Past
    val pastLocalDateValue: LocalDate? = null,
    @PastOrPresent
    val pastOrPresentLocalDateValue: LocalDate? = null,


    @Future
    val futureLocalTimeValue: LocalTime? = null,
    @FutureOrPresent
    val futureOrPresentLocalTimeValue: LocalTime? = null,
    @Past
    val pastLocalTimeValue: LocalTime? = null,
    @PastOrPresent
    val pastOrPresentLocalTimeValue: LocalTime? = null,


    @Future
    val futureLocalDateTimeValue: LocalDateTime? = null,
    @FutureOrPresent
    val futureOrPresentLocalDateTimeValue: LocalDateTime? = null,
    @Past
    val pastLocalDateTimeValue: LocalDateTime? = null,
    @PastOrPresent
    val pastOrPresentLocalDateTimeValue: LocalDateTime? = null,


    @Future
    val futureOffsetTimeValue: OffsetTime? = null,
    @FutureOrPresent
    val futureOrPresentOffsetTimeValue: OffsetTime? = null,
    @Past
    val pastOffsetTimeValue: OffsetTime? = null,
    @PastOrPresent
    val pastOrPresentOffsetTimeValue: OffsetTime? = null,


    @Future
    val futureOffsetDateTimeValue: OffsetDateTime? = null,
    @FutureOrPresent
    val futureOrPresentOffsetDateTimeValue: OffsetDateTime? = null,
    @Past
    val pastOffsetDateTimeValue: OffsetDateTime? = null,
    @PastOrPresent
    val pastOrPresentOffsetDateTimeValue: OffsetDateTime? = null,


    @Future
    val futureZonedDateTimeValue: ZonedDateTime? = null,
    @FutureOrPresent
    val futureOrPresentZonedDateTimeValue: ZonedDateTime? = null,
    @Past
    val pastZonedDateTimeValue: ZonedDateTime? = null,
    @PastOrPresent
    val pastOrPresentZonedDateTimeValue: ZonedDateTime? = null,


    @Future
    val futureYearValue: Year? = null,
    @FutureOrPresent
    val futureOrPresentYearValue: Year? = null,
    @Past
    val pastYearValue: Year? = null,
    @PastOrPresent
    val pastOrPresentYearValue: Year? = null,


    @Future
    val futureYearMonthValue: YearMonth? = null,
    @FutureOrPresent
    val futureOrPresentYearMonthValue: YearMonth? = null,
    @Past
    val pastYearMonthValue: YearMonth? = null,
    @PastOrPresent
    val pastOrPresentYearMonthValue: YearMonth? = null,


    @Future
    val futureMonthDayValue: MonthDay? = null,
    @FutureOrPresent
    val futureOrPresentMonthDayValue: MonthDay? = null,
    @Past
    val pastMonthDayValue: MonthDay? = null,
    @PastOrPresent
    val pastOrPresentMonthDayValue: MonthDay? = null,


    @Future
    val futureHijrahDateValue: HijrahDate? = null,
    @FutureOrPresent
    val futureOrPresentHijrahDateValue: HijrahDate? = null,
    @Past
    val pastHijrahDateValue: HijrahDate? = null,
    @PastOrPresent
    val pastOrPresentHijrahDateValue: HijrahDate? = null,


    @Future
    val futureJapaneseDateValue: JapaneseDate? = null,
    @FutureOrPresent
    val futureOrPresentJapaneseDateValue: JapaneseDate? = null,
    @Past
    val pastJapaneseDateValue: JapaneseDate? = null,
    @PastOrPresent
    val pastOrPresentJapaneseDateValue: JapaneseDate? = null,


    @Future
    val futureMinguoDateValue: MinguoDate? = null,
    @FutureOrPresent
    val futureOrPresentMinguoDateValue: MinguoDate? = null,
    @Past
    val pastMinguoDateValue: MinguoDate? = null,
    @PastOrPresent
    val pastOrPresentMinguoDateValue: MinguoDate? = null,


    @Future
    val futureThaiBuddhistDateValue: ThaiBuddhistDate? = null,
    @FutureOrPresent
    val futureOrPresentThaiBuddhistDateValue: ThaiBuddhistDate? = null,
    @Past
    val pastThaiBuddhistDateValue: ThaiBuddhistDate? = null,
    @PastOrPresent
    val pastOrPresentThaiBuddhistDateValue: ThaiBuddhistDate? = null,
)
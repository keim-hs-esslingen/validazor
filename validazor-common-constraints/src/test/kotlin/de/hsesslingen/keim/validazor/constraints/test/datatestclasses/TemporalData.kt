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
    @field:Future
    val futureDateValue: Date? = null,
    @field:FutureOrPresent
    val futureOrPresentDateValue: Date? = null,
    @field:Past
    val pastDateValue: Date? = null,
    @field:PastOrPresent
    val pastOrPresentDateValue: Date? = null,


    @field:Future
    val futureCalendarValue: Calendar? = null,
    @field:FutureOrPresent
    val futureOrPresentCalendarValue: Calendar? = null,
    @field:Past
    val pastCalendarValue: Calendar? = null,
    @field:PastOrPresent
    val pastOrPresentCalendarValue: Calendar? = null,


    @field:Future
    val futureInstantValue: Instant? = null,
    @field:FutureOrPresent
    val futureOrPresentInstantValue: Instant? = null,
    @field:Past
    val pastInstantValue: Instant? = null,
    @field:PastOrPresent
    val pastOrPresentInstantValue: Instant? = null,


    @field:Future
    val futureLocalDateValue: LocalDate? = null,
    @field:FutureOrPresent
    val futureOrPresentLocalDateValue: LocalDate? = null,
    @field:Past
    val pastLocalDateValue: LocalDate? = null,
    @field:PastOrPresent
    val pastOrPresentLocalDateValue: LocalDate? = null,


    @field:Future
    val futureLocalTimeValue: LocalTime? = null,
    @field:FutureOrPresent
    val futureOrPresentLocalTimeValue: LocalTime? = null,
    @field:Past
    val pastLocalTimeValue: LocalTime? = null,
    @field:PastOrPresent
    val pastOrPresentLocalTimeValue: LocalTime? = null,


    @field:Future
    val futureLocalDateTimeValue: LocalDateTime? = null,
    @field:FutureOrPresent
    val futureOrPresentLocalDateTimeValue: LocalDateTime? = null,
    @field:Past
    val pastLocalDateTimeValue: LocalDateTime? = null,
    @field:PastOrPresent
    val pastOrPresentLocalDateTimeValue: LocalDateTime? = null,


    @field:Future
    val futureOffsetTimeValue: OffsetTime? = null,
    @field:FutureOrPresent
    val futureOrPresentOffsetTimeValue: OffsetTime? = null,
    @field:Past
    val pastOffsetTimeValue: OffsetTime? = null,
    @field:PastOrPresent
    val pastOrPresentOffsetTimeValue: OffsetTime? = null,


    @field:Future
    val futureOffsetDateTimeValue: OffsetDateTime? = null,
    @field:FutureOrPresent
    val futureOrPresentOffsetDateTimeValue: OffsetDateTime? = null,
    @field:Past
    val pastOffsetDateTimeValue: OffsetDateTime? = null,
    @field:PastOrPresent
    val pastOrPresentOffsetDateTimeValue: OffsetDateTime? = null,


    @field:Future
    val futureZonedDateTimeValue: ZonedDateTime? = null,
    @field:FutureOrPresent
    val futureOrPresentZonedDateTimeValue: ZonedDateTime? = null,
    @field:Past
    val pastZonedDateTimeValue: ZonedDateTime? = null,
    @field:PastOrPresent
    val pastOrPresentZonedDateTimeValue: ZonedDateTime? = null,


    @field:Future
    val futureYearValue: Year? = null,
    @field:FutureOrPresent
    val futureOrPresentYearValue: Year? = null,
    @field:Past
    val pastYearValue: Year? = null,
    @field:PastOrPresent
    val pastOrPresentYearValue: Year? = null,


    @field:Future
    val futureYearMonthValue: YearMonth? = null,
    @field:FutureOrPresent
    val futureOrPresentYearMonthValue: YearMonth? = null,
    @field:Past
    val pastYearMonthValue: YearMonth? = null,
    @field:PastOrPresent
    val pastOrPresentYearMonthValue: YearMonth? = null,


    @field:Future
    val futureMonthDayValue: MonthDay? = null,
    @field:FutureOrPresent
    val futureOrPresentMonthDayValue: MonthDay? = null,
    @field:Past
    val pastMonthDayValue: MonthDay? = null,
    @field:PastOrPresent
    val pastOrPresentMonthDayValue: MonthDay? = null,


    @field:Future
    val futureHijrahDateValue: HijrahDate? = null,
    @field:FutureOrPresent
    val futureOrPresentHijrahDateValue: HijrahDate? = null,
    @field:Past
    val pastHijrahDateValue: HijrahDate? = null,
    @field:PastOrPresent
    val pastOrPresentHijrahDateValue: HijrahDate? = null,


    @field:Future
    val futureJapaneseDateValue: JapaneseDate? = null,
    @field:FutureOrPresent
    val futureOrPresentJapaneseDateValue: JapaneseDate? = null,
    @field:Past
    val pastJapaneseDateValue: JapaneseDate? = null,
    @field:PastOrPresent
    val pastOrPresentJapaneseDateValue: JapaneseDate? = null,


    @field:Future
    val futureMinguoDateValue: MinguoDate? = null,
    @field:FutureOrPresent
    val futureOrPresentMinguoDateValue: MinguoDate? = null,
    @field:Past
    val pastMinguoDateValue: MinguoDate? = null,
    @field:PastOrPresent
    val pastOrPresentMinguoDateValue: MinguoDate? = null,


    @field:Future
    val futureThaiBuddhistDateValue: ThaiBuddhistDate? = null,
    @field:FutureOrPresent
    val futureOrPresentThaiBuddhistDateValue: ThaiBuddhistDate? = null,
    @field:Past
    val pastThaiBuddhistDateValue: ThaiBuddhistDate? = null,
    @field:PastOrPresent
    val pastOrPresentThaiBuddhistDateValue: ThaiBuddhistDate? = null,
)
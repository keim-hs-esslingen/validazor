package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertValid
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.TemporalData
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class TemporalTest {

    private fun getFutureNowPast(): Triple<NowContext, NowContext, NowContext> {
        // Choosing a special now context for testing for which each field has a medium value.
        val nowRef = ZonedDateTime.of(2021, 6, 15, 12, 30, 30, 500000000, ZoneId.of("Z"))

        // Adding something to each field without overflow to produce increased values even if truncated to simpler types.
        val futureRef = nowRef
            .plusMinutes(20)
            .plusHours(1)
            .plusDays(1)
            .plusMonths(5)
            .plusYears(2)

        // Subtracting something from each field without overflow to produce decreased values even if truncated to simpler types.
        val pastRef = nowRef
            .minusMinutes(20)
            .minusHours(1)
            .minusDays(1)
            .minusMonths(5)
            .minusYears(2)

        return Triple(NowContext(futureRef), NowContext(nowRef), NowContext(pastRef))
    }

    @Test
    fun testNullValid() {
        assertValid(
            TemporalData(
                futureDateValue = null,
                futureOrPresentDateValue = null,
                pastDateValue = null,
                pastOrPresentDateValue = null,
                futureCalendarValue = null,
                futureOrPresentCalendarValue = null,
                pastCalendarValue = null,
                pastOrPresentCalendarValue = null,
                futureInstantValue = null,
                futureOrPresentInstantValue = null,
                pastInstantValue = null,
                pastOrPresentInstantValue = null,
                futureLocalDateValue = null,
                futureOrPresentLocalDateValue = null,
                pastLocalDateValue = null,
                pastOrPresentLocalDateValue = null,
                futureLocalTimeValue = null,
                futureOrPresentLocalTimeValue = null,
                pastLocalTimeValue = null,
                pastOrPresentLocalTimeValue = null,
                futureLocalDateTimeValue = null,
                futureOrPresentLocalDateTimeValue = null,
                pastLocalDateTimeValue = null,
                pastOrPresentLocalDateTimeValue = null,
                futureOffsetTimeValue = null,
                futureOrPresentOffsetTimeValue = null,
                pastOffsetTimeValue = null,
                pastOrPresentOffsetTimeValue = null,
                futureOffsetDateTimeValue = null,
                futureOrPresentOffsetDateTimeValue = null,
                pastOffsetDateTimeValue = null,
                pastOrPresentOffsetDateTimeValue = null,
                futureZonedDateTimeValue = null,
                futureOrPresentZonedDateTimeValue = null,
                pastZonedDateTimeValue = null,
                pastOrPresentZonedDateTimeValue = null,
                futureYearValue = null,
                futureOrPresentYearValue = null,
                pastYearValue = null,
                pastOrPresentYearValue = null,
                futureYearMonthValue = null,
                futureOrPresentYearMonthValue = null,
                pastYearMonthValue = null,
                pastOrPresentYearMonthValue = null,
                futureMonthDayValue = null,
                futureOrPresentMonthDayValue = null,
                pastMonthDayValue = null,
                pastOrPresentMonthDayValue = null,
                futureHijrahDateValue = null,
                futureOrPresentHijrahDateValue = null,
                pastHijrahDateValue = null,
                pastOrPresentHijrahDateValue = null,
                futureJapaneseDateValue = null,
                futureOrPresentJapaneseDateValue = null,
                pastJapaneseDateValue = null,
                pastOrPresentJapaneseDateValue = null,
                futureMinguoDateValue = null,
                futureOrPresentMinguoDateValue = null,
                pastMinguoDateValue = null,
                pastOrPresentMinguoDateValue = null,
                futureThaiBuddhistDateValue = null,
                futureOrPresentThaiBuddhistDateValue = null,
                pastThaiBuddhistDateValue = null,
                pastOrPresentThaiBuddhistDateValue = null,
            )
        )
    }

    @Test
    fun testValid() {
        val (future, now, past) = getFutureNowPast()

        assertValid(
            TemporalData(
                futureDateValue = future.asDate,
                futureOrPresentDateValue = future.asDate,
                pastDateValue = past.asDate,
                pastOrPresentDateValue = past.asDate,

                futureCalendarValue = future.asCalendar,
                futureOrPresentCalendarValue = future.asCalendar,
                pastCalendarValue = past.asCalendar,
                pastOrPresentCalendarValue = past.asCalendar,

                futureInstantValue = future.asInstant,
                futureOrPresentInstantValue = future.asInstant,
                pastInstantValue = past.asInstant,
                pastOrPresentInstantValue = past.asInstant,

                futureLocalDateValue = future.asLocalDate,
                futureOrPresentLocalDateValue = future.asLocalDate,
                pastLocalDateValue = past.asLocalDate,
                pastOrPresentLocalDateValue = past.asLocalDate,

                futureLocalTimeValue = future.asLocalTime,
                futureOrPresentLocalTimeValue = future.asLocalTime,
                pastLocalTimeValue = past.asLocalTime,
                pastOrPresentLocalTimeValue = past.asLocalTime,

                futureLocalDateTimeValue = future.asLocalDateTime,
                futureOrPresentLocalDateTimeValue = future.asLocalDateTime,
                pastLocalDateTimeValue = past.asLocalDateTime,
                pastOrPresentLocalDateTimeValue = past.asLocalDateTime,

                futureOffsetTimeValue = future.asOffsetTime,
                futureOrPresentOffsetTimeValue = future.asOffsetTime,
                pastOffsetTimeValue = past.asOffsetTime,
                pastOrPresentOffsetTimeValue = past.asOffsetTime,

                futureOffsetDateTimeValue = future.asOffsetDateTime,
                futureOrPresentOffsetDateTimeValue = future.asOffsetDateTime,
                pastOffsetDateTimeValue = past.asOffsetDateTime,
                pastOrPresentOffsetDateTimeValue = past.asOffsetDateTime,

                futureZonedDateTimeValue = future.asZonedDateTime,
                futureOrPresentZonedDateTimeValue = future.asZonedDateTime,
                pastZonedDateTimeValue = past.asZonedDateTime,
                pastOrPresentZonedDateTimeValue = past.asZonedDateTime,

                futureYearValue = future.asYear,
                futureOrPresentYearValue = future.asYear,
                pastYearValue = past.asYear,
                pastOrPresentYearValue = past.asYear,

                futureYearMonthValue = future.asYearMonth,
                futureOrPresentYearMonthValue = future.asYearMonth,
                pastYearMonthValue = past.asYearMonth,
                pastOrPresentYearMonthValue = past.asYearMonth,

                futureMonthDayValue = future.asMonthDay,
                futureOrPresentMonthDayValue = future.asMonthDay,
                pastMonthDayValue = past.asMonthDay,
                pastOrPresentMonthDayValue = past.asMonthDay,

                futureHijrahDateValue = future.asHijrahDate,
                futureOrPresentHijrahDateValue = future.asHijrahDate,
                pastHijrahDateValue = past.asHijrahDate,
                pastOrPresentHijrahDateValue = past.asHijrahDate,

                futureJapaneseDateValue = future.asJapaneseDate,
                futureOrPresentJapaneseDateValue = future.asJapaneseDate,
                pastJapaneseDateValue = past.asJapaneseDate,
                pastOrPresentJapaneseDateValue = past.asJapaneseDate,

                futureMinguoDateValue = future.asMinguoDate,
                futureOrPresentMinguoDateValue = future.asMinguoDate,
                pastMinguoDateValue = past.asMinguoDate,
                pastOrPresentMinguoDateValue = past.asMinguoDate,

                futureThaiBuddhistDateValue = future.asThaiBuddhistDate,
                futureOrPresentThaiBuddhistDateValue = future.asThaiBuddhistDate,
                pastThaiBuddhistDateValue = past.asThaiBuddhistDate,
                pastOrPresentThaiBuddhistDateValue = past.asThaiBuddhistDate,
            ),
            nowContext = now
        )
    }
}
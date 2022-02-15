package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.constraints.test.assertValid
import de.hsesslingen.keim.validazor.constraints.test.datatestclasses.TemporalData
import de.hsesslingen.keim.validazor.constraints.test.validate
import de.hsesslingen.keim.validazor.test.assertMessage
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

    @Test
    fun testInvalid() {
        val (future, now, past) = getFutureNowPast()

        // Give past values to future properties and vice versa.
        val violations = validate(
            TemporalData(
                futureDateValue = past.asDate,
                futureOrPresentDateValue = past.asDate,
                pastDateValue = future.asDate,
                pastOrPresentDateValue = future.asDate,

                futureCalendarValue = past.asCalendar,
                futureOrPresentCalendarValue = past.asCalendar,
                pastCalendarValue = future.asCalendar,
                pastOrPresentCalendarValue = future.asCalendar,

                futureInstantValue = past.asInstant,
                futureOrPresentInstantValue = past.asInstant,
                pastInstantValue = future.asInstant,
                pastOrPresentInstantValue = future.asInstant,

                futureLocalDateValue = past.asLocalDate,
                futureOrPresentLocalDateValue = past.asLocalDate,
                pastLocalDateValue = future.asLocalDate,
                pastOrPresentLocalDateValue = future.asLocalDate,

                futureLocalTimeValue = past.asLocalTime,
                futureOrPresentLocalTimeValue = past.asLocalTime,
                pastLocalTimeValue = future.asLocalTime,
                pastOrPresentLocalTimeValue = future.asLocalTime,

                futureLocalDateTimeValue = past.asLocalDateTime,
                futureOrPresentLocalDateTimeValue = past.asLocalDateTime,
                pastLocalDateTimeValue = future.asLocalDateTime,
                pastOrPresentLocalDateTimeValue = future.asLocalDateTime,

                futureOffsetTimeValue = past.asOffsetTime,
                futureOrPresentOffsetTimeValue = past.asOffsetTime,
                pastOffsetTimeValue = future.asOffsetTime,
                pastOrPresentOffsetTimeValue = future.asOffsetTime,

                futureOffsetDateTimeValue = past.asOffsetDateTime,
                futureOrPresentOffsetDateTimeValue = past.asOffsetDateTime,
                pastOffsetDateTimeValue = future.asOffsetDateTime,
                pastOrPresentOffsetDateTimeValue = future.asOffsetDateTime,

                futureZonedDateTimeValue = past.asZonedDateTime,
                futureOrPresentZonedDateTimeValue = past.asZonedDateTime,
                pastZonedDateTimeValue = future.asZonedDateTime,
                pastOrPresentZonedDateTimeValue = future.asZonedDateTime,

                futureYearValue = past.asYear,
                futureOrPresentYearValue = past.asYear,
                pastYearValue = future.asYear,
                pastOrPresentYearValue = future.asYear,

                futureYearMonthValue = past.asYearMonth,
                futureOrPresentYearMonthValue = past.asYearMonth,
                pastYearMonthValue = future.asYearMonth,
                pastOrPresentYearMonthValue = future.asYearMonth,

                futureMonthDayValue = past.asMonthDay,
                futureOrPresentMonthDayValue = past.asMonthDay,
                pastMonthDayValue = future.asMonthDay,
                pastOrPresentMonthDayValue = future.asMonthDay,

                futureHijrahDateValue = past.asHijrahDate,
                futureOrPresentHijrahDateValue = past.asHijrahDate,
                pastHijrahDateValue = future.asHijrahDate,
                pastOrPresentHijrahDateValue = future.asHijrahDate,

                futureJapaneseDateValue = past.asJapaneseDate,
                futureOrPresentJapaneseDateValue = past.asJapaneseDate,
                pastJapaneseDateValue = future.asJapaneseDate,
                pastOrPresentJapaneseDateValue = future.asJapaneseDate,

                futureMinguoDateValue = past.asMinguoDate,
                futureOrPresentMinguoDateValue = past.asMinguoDate,
                pastMinguoDateValue = future.asMinguoDate,
                pastOrPresentMinguoDateValue = future.asMinguoDate,

                futureThaiBuddhistDateValue = past.asThaiBuddhistDate,
                futureOrPresentThaiBuddhistDateValue = past.asThaiBuddhistDate,
                pastThaiBuddhistDateValue = future.asThaiBuddhistDate,
                pastOrPresentThaiBuddhistDateValue = future.asThaiBuddhistDate,
            ),
            nowContext = now
        )

        violations.assertMessage("must be in past")
            .presentFor("pastDateValue")
            .presentFor("pastCalendarValue")
            .presentFor("pastInstantValue")
            .presentFor("pastLocalDateValue")
            .presentFor("pastLocalTimeValue")
            .presentFor("pastLocalDateTimeValue")
            .presentFor("pastOffsetTimeValue")
            .presentFor("pastOffsetDateTimeValue")
            .presentFor("pastZonedDateTimeValue")
            .presentFor("pastYearValue")
            .presentFor("pastYearMonthValue")
            .presentFor("pastMonthDayValue")
            .presentFor("pastHijrahDateValue")
            .presentFor("pastJapaneseDateValue")
            .presentFor("pastMinguoDateValue")
            .presentFor("pastThaiBuddhistDateValue")

        violations.assertMessage("must be in past or present")
            .presentFor("pastOrPresentDateValue")
            .presentFor("pastOrPresentCalendarValue")
            .presentFor("pastOrPresentInstantValue")
            .presentFor("pastOrPresentLocalDateValue")
            .presentFor("pastOrPresentLocalTimeValue")
            .presentFor("pastOrPresentLocalDateTimeValue")
            .presentFor("pastOrPresentOffsetTimeValue")
            .presentFor("pastOrPresentOffsetDateTimeValue")
            .presentFor("pastOrPresentZonedDateTimeValue")
            .presentFor("pastOrPresentYearValue")
            .presentFor("pastOrPresentYearMonthValue")
            .presentFor("pastOrPresentMonthDayValue")
            .presentFor("pastOrPresentHijrahDateValue")
            .presentFor("pastOrPresentJapaneseDateValue")
            .presentFor("pastOrPresentMinguoDateValue")
            .presentFor("pastOrPresentThaiBuddhistDateValue")

        violations.assertMessage("must be in future or present")
            .presentFor("futureOrPresentDateValue")
            .presentFor("futureOrPresentCalendarValue")
            .presentFor("futureOrPresentInstantValue")
            .presentFor("futureOrPresentLocalDateValue")
            .presentFor("futureOrPresentLocalTimeValue")
            .presentFor("futureOrPresentLocalDateTimeValue")
            .presentFor("futureOrPresentOffsetTimeValue")
            .presentFor("futureOrPresentOffsetDateTimeValue")
            .presentFor("futureOrPresentZonedDateTimeValue")
            .presentFor("futureOrPresentYearValue")
            .presentFor("futureOrPresentYearMonthValue")
            .presentFor("futureOrPresentMonthDayValue")
            .presentFor("futureOrPresentHijrahDateValue")
            .presentFor("futureOrPresentJapaneseDateValue")
            .presentFor("futureOrPresentMinguoDateValue")
            .presentFor("futureOrPresentThaiBuddhistDateValue")

        violations.assertMessage("must be in future")
            .presentFor("futureDateValue")
            .presentFor("futureCalendarValue")
            .presentFor("futureInstantValue")
            .presentFor("futureLocalDateValue")
            .presentFor("futureLocalTimeValue")
            .presentFor("futureLocalDateTimeValue")
            .presentFor("futureOffsetTimeValue")
            .presentFor("futureOffsetDateTimeValue")
            .presentFor("futureZonedDateTimeValue")
            .presentFor("futureYearValue")
            .presentFor("futureYearMonthValue")
            .presentFor("futureMonthDayValue")
            .presentFor("futureHijrahDateValue")
            .presentFor("futureJapaneseDateValue")
            .presentFor("futureMinguoDateValue")
            .presentFor("futureThaiBuddhistDateValue")
    }

    @Test
    fun testInvalidWithPresentValues() {
        val (future, now, past) = getFutureNowPast()

        // Give past values to future properties and vice versa.
        val violations = validate(
            TemporalData(
                futureDateValue = now.asDate,
                futureOrPresentDateValue = now.asDate,
                pastDateValue = now.asDate,
                pastOrPresentDateValue = now.asDate,

                futureCalendarValue = now.asCalendar,
                futureOrPresentCalendarValue = now.asCalendar,
                pastCalendarValue = now.asCalendar,
                pastOrPresentCalendarValue = now.asCalendar,

                futureInstantValue = now.asInstant,
                futureOrPresentInstantValue = now.asInstant,
                pastInstantValue = now.asInstant,
                pastOrPresentInstantValue = now.asInstant,

                futureLocalDateValue = now.asLocalDate,
                futureOrPresentLocalDateValue = now.asLocalDate,
                pastLocalDateValue = now.asLocalDate,
                pastOrPresentLocalDateValue = now.asLocalDate,

                futureLocalTimeValue = now.asLocalTime,
                futureOrPresentLocalTimeValue = now.asLocalTime,
                pastLocalTimeValue = now.asLocalTime,
                pastOrPresentLocalTimeValue = now.asLocalTime,

                futureLocalDateTimeValue = now.asLocalDateTime,
                futureOrPresentLocalDateTimeValue = now.asLocalDateTime,
                pastLocalDateTimeValue = now.asLocalDateTime,
                pastOrPresentLocalDateTimeValue = now.asLocalDateTime,

                futureOffsetTimeValue = now.asOffsetTime,
                futureOrPresentOffsetTimeValue = now.asOffsetTime,
                pastOffsetTimeValue = now.asOffsetTime,
                pastOrPresentOffsetTimeValue = now.asOffsetTime,

                futureOffsetDateTimeValue = now.asOffsetDateTime,
                futureOrPresentOffsetDateTimeValue = now.asOffsetDateTime,
                pastOffsetDateTimeValue = now.asOffsetDateTime,
                pastOrPresentOffsetDateTimeValue = now.asOffsetDateTime,

                futureZonedDateTimeValue = now.asZonedDateTime,
                futureOrPresentZonedDateTimeValue = now.asZonedDateTime,
                pastZonedDateTimeValue = now.asZonedDateTime,
                pastOrPresentZonedDateTimeValue = now.asZonedDateTime,

                futureYearValue = now.asYear,
                futureOrPresentYearValue = now.asYear,
                pastYearValue = now.asYear,
                pastOrPresentYearValue = now.asYear,

                futureYearMonthValue = now.asYearMonth,
                futureOrPresentYearMonthValue = now.asYearMonth,
                pastYearMonthValue = now.asYearMonth,
                pastOrPresentYearMonthValue = now.asYearMonth,

                futureMonthDayValue = now.asMonthDay,
                futureOrPresentMonthDayValue = now.asMonthDay,
                pastMonthDayValue = now.asMonthDay,
                pastOrPresentMonthDayValue = now.asMonthDay,

                futureHijrahDateValue = now.asHijrahDate,
                futureOrPresentHijrahDateValue = now.asHijrahDate,
                pastHijrahDateValue = now.asHijrahDate,
                pastOrPresentHijrahDateValue = now.asHijrahDate,

                futureJapaneseDateValue = now.asJapaneseDate,
                futureOrPresentJapaneseDateValue = now.asJapaneseDate,
                pastJapaneseDateValue = now.asJapaneseDate,
                pastOrPresentJapaneseDateValue = now.asJapaneseDate,

                futureMinguoDateValue = now.asMinguoDate,
                futureOrPresentMinguoDateValue = now.asMinguoDate,
                pastMinguoDateValue = now.asMinguoDate,
                pastOrPresentMinguoDateValue = now.asMinguoDate,

                futureThaiBuddhistDateValue = now.asThaiBuddhistDate,
                futureOrPresentThaiBuddhistDateValue = now.asThaiBuddhistDate,
                pastThaiBuddhistDateValue = now.asThaiBuddhistDate,
                pastOrPresentThaiBuddhistDateValue = now.asThaiBuddhistDate,
            ),
            nowContext = now
        )

        violations.assertMessage("must be in past")
            .presentFor("pastDateValue")
            .presentFor("pastCalendarValue")
            .presentFor("pastInstantValue")
            .presentFor("pastLocalDateValue")
            .presentFor("pastLocalTimeValue")
            .presentFor("pastLocalDateTimeValue")
            .presentFor("pastOffsetTimeValue")
            .presentFor("pastOffsetDateTimeValue")
            .presentFor("pastZonedDateTimeValue")
            .presentFor("pastYearValue")
            .presentFor("pastYearMonthValue")
            .presentFor("pastMonthDayValue")
            .presentFor("pastHijrahDateValue")
            .presentFor("pastJapaneseDateValue")
            .presentFor("pastMinguoDateValue")
            .presentFor("pastThaiBuddhistDateValue")

        violations.assertMessage("must be in past or present")
            .notPresentFor("pastOrPresentDateValue")
            .notPresentFor("pastOrPresentCalendarValue")
            .notPresentFor("pastOrPresentInstantValue")
            .notPresentFor("pastOrPresentLocalDateValue")
            .notPresentFor("pastOrPresentLocalTimeValue")
            .notPresentFor("pastOrPresentLocalDateTimeValue")
            .notPresentFor("pastOrPresentOffsetTimeValue")
            .notPresentFor("pastOrPresentOffsetDateTimeValue")
            .notPresentFor("pastOrPresentZonedDateTimeValue")
            .notPresentFor("pastOrPresentYearValue")
            .notPresentFor("pastOrPresentYearMonthValue")
            .notPresentFor("pastOrPresentMonthDayValue")
            .notPresentFor("pastOrPresentHijrahDateValue")
            .notPresentFor("pastOrPresentJapaneseDateValue")
            .notPresentFor("pastOrPresentMinguoDateValue")
            .notPresentFor("pastOrPresentThaiBuddhistDateValue")

        violations.assertMessage("must be in future or present")
            .notPresentFor("futureOrPresentDateValue")
            .notPresentFor("futureOrPresentCalendarValue")
            .notPresentFor("futureOrPresentInstantValue")
            .notPresentFor("futureOrPresentLocalDateValue")
            .notPresentFor("futureOrPresentLocalTimeValue")
            .notPresentFor("futureOrPresentLocalDateTimeValue")
            .notPresentFor("futureOrPresentOffsetTimeValue")
            .notPresentFor("futureOrPresentOffsetDateTimeValue")
            .notPresentFor("futureOrPresentZonedDateTimeValue")
            .notPresentFor("futureOrPresentYearValue")
            .notPresentFor("futureOrPresentYearMonthValue")
            .notPresentFor("futureOrPresentMonthDayValue")
            .notPresentFor("futureOrPresentHijrahDateValue")
            .notPresentFor("futureOrPresentJapaneseDateValue")
            .notPresentFor("futureOrPresentMinguoDateValue")
            .notPresentFor("futureOrPresentThaiBuddhistDateValue")

        violations.assertMessage("must be in future")
            .presentFor("futureDateValue")
            .presentFor("futureCalendarValue")
            .presentFor("futureInstantValue")
            .presentFor("futureLocalDateValue")
            .presentFor("futureLocalTimeValue")
            .presentFor("futureLocalDateTimeValue")
            .presentFor("futureOffsetTimeValue")
            .presentFor("futureOffsetDateTimeValue")
            .presentFor("futureZonedDateTimeValue")
            .presentFor("futureYearValue")
            .presentFor("futureYearMonthValue")
            .presentFor("futureMonthDayValue")
            .presentFor("futureHijrahDateValue")
            .presentFor("futureJapaneseDateValue")
            .presentFor("futureMinguoDateValue")
            .presentFor("futureThaiBuddhistDateValue")
    }
}
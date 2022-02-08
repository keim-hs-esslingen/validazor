package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.constraints.jakarta.test.assertValid
import de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses.TemporalData
import org.junit.jupiter.api.Test
import java.util.*

fun Date.plusMillis(millis: Long): Date {
    return Date(this.time + millis)
}

fun Date.minusMillis(millis: Long): Date {
    return Date(this.time - millis)
}

fun Calendar.plusMillis(millis: Long): Calendar {
    this.add(Calendar.MILLISECOND, millis.toInt())
    return this
}

fun Calendar.minusMillis(millis: Long): Calendar {
    this.add(Calendar.MILLISECOND, -millis.toInt())
    return this
}

private const val MANY = 1000000000L

class TemporalTest {

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
        assertValid(
            TemporalData(
                futureDateValue = Date().plusMillis(MANY),
                futureOrPresentDateValue = Date().plusMillis(MANY),
                pastDateValue = Date().minusMillis(MANY),
                pastOrPresentDateValue = Date().minusMillis(MANY),

                futureCalendarValue = Calendar.getInstance().plusMillis(MANY),
                futureOrPresentCalendarValue = Calendar.getInstance().plusMillis(MANY),
                pastCalendarValue = Calendar.getInstance().minusMillis(MANY),
                pastOrPresentCalendarValue = Calendar.getInstance().minusMillis(MANY),

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
}
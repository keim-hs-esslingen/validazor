package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import jakarta.validation.constraints.FutureOrPresent
import java.time.*
import java.util.*

/**
 * A [ConstraintValidazor] for the Jakarta [FutureOrPresent] constraint.
 */
class FutureOrPresentValidazor : ConstraintValidazor<FutureOrPresent> {
    override fun validate(
        constraint: FutureOrPresent,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean
    ) {
        checkConstraint("must be in future or present", path, constraint, violations) {
            when (value) {
                is Instant -> !value.isBefore(Instant.now())
                is LocalTime -> !value.isBefore(LocalTime.now())
                is LocalDateTime -> !value.isBefore(LocalDateTime.now())
                is LocalDate -> !value.isBefore(LocalDate.now())
                is OffsetDateTime -> !value.isBefore(OffsetDateTime.now())
                is OffsetTime -> !value.isBefore(OffsetTime.now())
                is ZonedDateTime -> !value.isBefore(ZonedDateTime.now())
                is Year -> !value.isBefore(Year.now())
                is YearMonth -> !value.isBefore(YearMonth.now())
                is MonthDay -> !value.isBefore(MonthDay.now())
                is Date -> value.time >= Date().time
                is Calendar -> value.timeInMillis >= Calendar.getInstance().timeInMillis
                else -> true
            }
        }
    }
}
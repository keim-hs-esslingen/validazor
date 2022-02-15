package de.hsesslingen.keim.validazor.constraints.jakarta.test

import de.hsesslingen.keim.validazor.NowContext
import de.hsesslingen.keim.validazor.NowContext.Companion.fromSystemNow
import de.hsesslingen.keim.validazor.Validazor
import de.hsesslingen.keim.validazor.Violation
import de.hsesslingen.keim.validazor.constraints.jakarta.registerJakartaConstraints

fun validazor(): Validazor {
    return Validazor.Builder()
        .registerJakartaConstraints()
        .build()
}

fun validate(obj: Any, nowContext: NowContext = fromSystemNow()): List<Violation> {
    return validazor().validate(obj, nowContext)
}

fun assertValid(obj: Any, nowContext: NowContext = fromSystemNow()) {
    validazor().assertValid(obj, nowContext)
}

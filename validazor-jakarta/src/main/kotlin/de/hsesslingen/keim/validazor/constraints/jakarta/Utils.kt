package de.hsesslingen.keim.validazor.constraints.jakarta

import jakarta.validation.constraints.Pattern
import java.math.BigDecimal
import kotlin.math.ceil
import kotlin.math.log10
import kotlin.math.roundToInt

/**
 * Merges an array of pattern flags of a [jakarta.validation.constraints.Pattern] annotation using bitwise OR
 * to create a single integer flag-bitmask which can be used with a [java.util.regex.Pattern].
 */
@Suppress("SpellCheckingInspection")
fun Array<Pattern.Flag>.merge(): Int {
    return this.asSequence()
        .map { it.value /* is JDK-Value */ }
        .reduceOrNull() { a, b -> a or b }
        ?: 0
}

/**
 * Determines the number of integer digits of this [Double].
 */
fun Double.integerDigits(): Int {
    if (this == 0.0 || this.isNaN()) {
        return 0
    }

    if (this.isInfinite()) {
        return Int.MAX_VALUE // As near as we can get to infinity.
    }

    return ceil(log10(this)).roundToInt()
}

/**
 * Checks whether this [BigDecimal] matches the given constraints.
 */
fun BigDecimal.matchesDigitConstraints(maxIntegerDigits: Int, maxFractionDigits: Int): Boolean {
    val stripped = this.stripTrailingZeros()
    val strippedScale = stripped.scale()
    val strippedPrecision = stripped.precision()
    return (strippedPrecision - strippedScale) <= maxIntegerDigits && strippedScale <= maxFractionDigits
}

/**
 * This pattern can be used to validate email addresses.
 *
 * It was copied from http://emailregex.com/
 */
val VALID_EMAIL_PATTERN by lazy {
    @Suppress("RegExpRedundantEscape")
    Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
}

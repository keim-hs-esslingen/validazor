package de.hsesslingen.keim.validazor.constraints

import java.math.BigDecimal

/**
 * Merges an array of integers using bitwise OR.
 */
fun IntArray.mergeWithOr(): Int {
    return this.reduceOrNull { a, b -> a or b } ?: 0
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

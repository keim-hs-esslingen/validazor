package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.Email


class EmailData(
    @Email
    val stringValue: String? = null,
    @Email
    val charSequenceValue: CharSequence? = null,
    @Email(regexp = EXTRA_PATTERN)
    val stringValueWithPattern: String? = null,
    @Email(regexp = EXTRA_PATTERN)
    val charSequenceValueWithPattern: CharSequence? = null,
) {
    companion object {
        const val EXTRA_PATTERN = "[abc]+@hs-esslingen\\.de"
    }
}
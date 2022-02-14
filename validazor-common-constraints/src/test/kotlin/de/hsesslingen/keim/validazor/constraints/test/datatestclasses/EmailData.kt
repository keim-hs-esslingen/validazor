package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.Email


class EmailData(
    @field:Email
    val stringValue: String? = null,
    @field:Email
    val charSequenceValue: CharSequence? = null,
    @field:Email(regexp = EXTRA_PATTERN)
    val stringValueWithPattern: String? = null,
    @field:Email(regexp = EXTRA_PATTERN)
    val charSequenceValueWithPattern: CharSequence? = null,
) {
    companion object {
        const val EXTRA_PATTERN = "[abc]+@hs-esslingen\\.de"
    }
}
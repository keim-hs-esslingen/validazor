package de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses

import jakarta.validation.constraints.Pattern

class PatternData(
    @field:Pattern(regexp = PATTERN)
    val charSequenceValue: CharSequence = "",
    @field:Pattern(regexp = PATTERN)
    val stringValue: String = "",
) {
    companion object {
        const val PATTERN = "[a-zA-Z]*"
    }
}
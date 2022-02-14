package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.Pattern

class PatternData(
    @Pattern(regexp = PATTERN)
    val charSequenceValue: CharSequence = "",
    @Pattern(regexp = PATTERN)
    val stringValue: String = "",
) {
    companion object {
        const val PATTERN = "[a-zA-Z]*"
    }
}
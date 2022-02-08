package de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses

import jakarta.validation.constraints.Pattern

class PatternData(
    @field:Pattern(regexp = "[a-zA-Z]*")
    val charSequenceValue: CharSequence = "",
    @field:Pattern(regexp = "[a-zA-Z]*")
    val stringValue: String = "",
)
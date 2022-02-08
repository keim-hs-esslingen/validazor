package de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses

import jakarta.validation.constraints.NotBlank

class NotBlankData(
    @field:NotBlank
    val stringValue: String? = null,
    @field:NotBlank
    val charSequenceValue: CharSequence? = null,
)
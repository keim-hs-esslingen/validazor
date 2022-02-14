package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.NotBlank

class NotBlankData(
    @field:NotBlank
    val stringValue: String? = null,
    @field:NotBlank
    val charSequenceValue: CharSequence? = null,
)
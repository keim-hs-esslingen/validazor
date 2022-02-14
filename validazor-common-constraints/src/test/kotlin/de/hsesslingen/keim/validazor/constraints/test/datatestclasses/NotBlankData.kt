package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.NotBlank

class NotBlankData(
    @NotBlank
    val stringValue: String? = null,
    @NotBlank
    val charSequenceValue: CharSequence? = null,
)
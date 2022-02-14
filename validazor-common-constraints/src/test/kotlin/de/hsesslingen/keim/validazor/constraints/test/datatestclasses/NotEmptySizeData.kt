package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.NotEmpty
import de.hsesslingen.keim.validazor.constraints.Size


class NotEmptySizeData(
    @NotEmpty
    @Size(min = MIN_SIZE, max = MAX_SIZE)
    val stringValue: String? = null,
    @NotEmpty
    @Size(min = MIN_SIZE, max = MAX_SIZE)
    val charSequenceValue: CharSequence? = null,
    @NotEmpty
    @Size(min = MIN_SIZE, max = MAX_SIZE)
    val listValue: List<*>? = null,
    @NotEmpty
    @Size(min = MIN_SIZE, max = MAX_SIZE)
    val setValue: Set<*>? = null,
    @NotEmpty
    @Size(min = MIN_SIZE, max = MAX_SIZE)
    val mapValue: Map<*, *>? = null,
    @NotEmpty
    @Size(min = MIN_SIZE, max = MAX_SIZE)
    val collectionValue: Collection<*>? = null,
    @NotEmpty
    @Size(min = MIN_SIZE, max = MAX_SIZE)
    val arrayValue: Array<*>? = null,
) {
    companion object {
        const val MIN_SIZE = 2
        const val MAX_SIZE = 3
    }
}
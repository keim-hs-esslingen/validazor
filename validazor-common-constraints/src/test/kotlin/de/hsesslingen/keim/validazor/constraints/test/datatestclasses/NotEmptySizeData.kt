package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.NotEmpty
import de.hsesslingen.keim.validazor.constraints.Size


class NotEmptySizeData(
    @field:NotEmpty
    @field:Size(min = MIN_SIZE, max = MAX_SIZE)
    val stringValue: String? = null,
    @field:NotEmpty
    @field:Size(min = MIN_SIZE, max = MAX_SIZE)
    val charSequenceValue: CharSequence? = null,
    @field:NotEmpty
    @field:Size(min = MIN_SIZE, max = MAX_SIZE)
    val listValue: List<*>? = null,
    @field:NotEmpty
    @field:Size(min = MIN_SIZE, max = MAX_SIZE)
    val setValue: Set<*>? = null,
    @field:NotEmpty
    @field:Size(min = MIN_SIZE, max = MAX_SIZE)
    val mapValue: Map<*, *>? = null,
    @field:NotEmpty
    @field:Size(min = MIN_SIZE, max = MAX_SIZE)
    val collectionValue: Collection<*>? = null,
    @field:NotEmpty
    @field:Size(min = MIN_SIZE, max = MAX_SIZE)
    val arrayValue: Array<*>? = null,
) {
    companion object {
        const val MIN_SIZE = 2
        const val MAX_SIZE = 3
    }
}
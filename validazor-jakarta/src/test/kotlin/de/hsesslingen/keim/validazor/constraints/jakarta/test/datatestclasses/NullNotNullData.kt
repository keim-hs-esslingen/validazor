package de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses

import jakarta.validation.constraints.*

class NullNotNullData(
    @field:Null
    val nullValue: Any? = null,

    @field:NotNull
    val notNullValue: Any? = Any(),
)
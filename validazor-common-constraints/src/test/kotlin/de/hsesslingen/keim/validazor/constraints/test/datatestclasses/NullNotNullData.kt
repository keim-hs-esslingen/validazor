package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.NotNull
import de.hsesslingen.keim.validazor.constraints.Null

class NullNotNullData(
    @field:Null
    val nullValue: Any? = null,

    @field:NotNull
    val notNullValue: Any? = Any(),
)
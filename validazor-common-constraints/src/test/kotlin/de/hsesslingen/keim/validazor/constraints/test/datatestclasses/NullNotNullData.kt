package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.NotNull
import de.hsesslingen.keim.validazor.constraints.Null

class NullNotNullData(
    @Null
    val nullValue: Any? = null,

    @NotNull
    val notNullValue: Any? = Any(),
)
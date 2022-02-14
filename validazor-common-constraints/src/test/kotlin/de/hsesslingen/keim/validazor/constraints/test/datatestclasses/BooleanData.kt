package de.hsesslingen.keim.validazor.constraints.test.datatestclasses

import de.hsesslingen.keim.validazor.constraints.AssertFalse
import de.hsesslingen.keim.validazor.constraints.AssertTrue

class BooleanData(
    @AssertTrue
    val trueValue: Boolean? = null,
    @AssertFalse
    val falseValue: Boolean? = null,
)
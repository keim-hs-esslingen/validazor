package de.hsesslingen.keim.validazor.constraints.jakarta.test.datatestclasses

import jakarta.validation.constraints.AssertFalse
import jakarta.validation.constraints.AssertTrue

class BooleanData(
    @field:AssertTrue
    val trueValue: Boolean? = null,
    @field:AssertFalse
    val falseValue: Boolean? = null,
)
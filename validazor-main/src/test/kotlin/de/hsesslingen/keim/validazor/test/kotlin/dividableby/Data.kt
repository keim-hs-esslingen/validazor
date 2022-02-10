package de.hsesslingen.keim.validazor.test.kotlin.dividableby

import de.hsesslingen.keim.validazor.test.java.dividableby.DividableBy

class Data (
    @field:DividableBy(2)
    val intValue: Int,
    @field:DividableBy(3)
    val longValue: Long,
)
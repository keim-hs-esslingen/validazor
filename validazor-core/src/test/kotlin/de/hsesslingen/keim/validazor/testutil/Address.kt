package de.hsesslingen.keim.validazor.testutil

const val VALID_STREET = "Kanalstrasse"
const val INVALID_STREET = "Charlottenplatz"
val INVALID_STREET_MSG = ToStringEquals.makeMessage(VALID_STREET)

const val VALID_CITY = "Esslingen"
const val INVALID_CITY = "Stuttgart"
val INVALID_CITY_MSG = ToStringEquals.makeMessage(VALID_CITY)

const val VALID_ZIP = "whatever"
val INVALID_ZIP = null
const val INVALID_ZIP_MSG = NotNull.MSG

@InhabitantsCorrectAddress
class Address(
    @field:ToStringEquals(VALID_STREET)
    var street: String,
    @field:ToStringEquals(VALID_CITY)
    var city: String,
    @field:NotNull
    var zip: String?,
    var inhabitants: Collection<Person>? = null
)
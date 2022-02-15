package de.hsesslingen.keim.validazor.testutils

const val VALID_NAME = "John Doe"
const val INVALID_NAME = "Mr X"
val INVALID_NAME_MSG = ToStringEquals.makeMessage(VALID_NAME)

const val VALID_EMAIL = "jon.doe@hs-esslingen.de"
const val INVALID_EMAIL = "jon.doe@acme.com"
val INVALID_EMAIL_MSG = ToStringEquals.makeMessage(VALID_EMAIL)

private const val VALID_AGE_STR = "30"
val VALID_AGE = VALID_AGE_STR.toInt()
private const val INVALID_AGE_STR = "29"
val INVALID_AGE = INVALID_AGE_STR.toInt()
val INVALID_AGE_MSG = ToStringEquals.makeMessage(VALID_AGE_STR)

const val OLDER_AS_VALID_AGE = 31
const val YOUNGER_AS_VALID_AGE = 29

@OlderThanFriends
class Person(
    @field:ToStringEquals(VALID_NAME)
    var name: String,
    @field:ToStringEquals(VALID_AGE_STR)
    var age: Int,
    @field:NotNull
    @field:ToStringEquals(VALID_EMAIL)
    var email: String?,
    var address: Address?,
    @field:NotNull
    var friends: List<Person>?,
    var mappedFriends: Map<Any, Person>? = null
) {
    override fun toString(): String {
        return this.name
    }
}
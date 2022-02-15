package de.hsesslingen.keim.validazor.test

typealias MessageTest = (msg: String) -> Boolean

fun startingWith(messagePrefix: String): MessageTest {
    return { it.startsWith(messagePrefix) }
}

fun endingWith(messageSuffix: String): MessageTest {
    return { it.startsWith(messageSuffix) }
}
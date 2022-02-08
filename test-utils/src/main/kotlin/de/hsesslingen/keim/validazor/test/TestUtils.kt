package de.hsesslingen.keim.validazor.test

import org.junit.jupiter.api.Assertions

fun <T> Collection<T>.assertAny(test: (it: T) -> Boolean) {
    return Assertions.assertTrue(this.any(test))
}

fun <T> Collection<T>.assertNone(test: (it: T) -> Boolean) {
    return Assertions.assertTrue(this.none(test))
}
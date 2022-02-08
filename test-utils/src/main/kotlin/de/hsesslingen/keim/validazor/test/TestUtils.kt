package de.hsesslingen.keim.validazor.test

import org.junit.jupiter.api.Assertions



fun <T, C : Collection<T>> C.assertAny(test: (it: T) -> Boolean): C {
    Assertions.assertTrue(this.any(test))
    return this
}

fun <T, C : Collection<T>> C.assertNone(test: (it: T) -> Boolean): C {
    Assertions.assertTrue(this.none(test))
    return this
}
package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.constraints.test.assertValid
import de.hsesslingen.keim.validazor.constraints.test.datatestclasses.NotEmptySizeData
import de.hsesslingen.keim.validazor.constraints.test.datatestclasses.NotEmptySizeData.Companion.MAX_SIZE
import de.hsesslingen.keim.validazor.constraints.test.datatestclasses.NotEmptySizeData.Companion.MIN_SIZE
import de.hsesslingen.keim.validazor.constraints.test.validate
import de.hsesslingen.keim.validazor.test.assertMessage
import org.junit.jupiter.api.Test

class NotEmptySizeTest {

    @Test
    fun testValid() {
        assertValid(
            NotEmptySizeData(
                stringValue = " ".repeat(MIN_SIZE),
                charSequenceValue = " ".repeat(MIN_SIZE),
                mapValue = mapOf(1 to 1, 2 to 2),
                collectionValue = listOf(1, 2),
                listValue = listOf(1, 2),
                setValue = setOf(1, 2),
                arrayValue = arrayOf(1, 2),
            )
        )

        assertValid(
            NotEmptySizeData(
                stringValue = " ".repeat(MAX_SIZE),
                charSequenceValue = " ".repeat(MAX_SIZE),
                mapValue = mapOf(1 to 1, 2 to 2, 3 to 3),
                collectionValue = listOf(1, 2, 3),
                listValue = listOf(1, 2, 3),
                setValue = setOf(1, 2, 3),
                arrayValue = arrayOf(1, 2, 3),
            )
        )

        assertValid(
            NotEmptySizeData(
                stringValue = null,
                charSequenceValue = null,
                mapValue = null,
                collectionValue = null,
                listValue = null,
                setValue = null,
                arrayValue = null,
            )
        )

        assertValid(
            NotEmptySizeData(
                stringValue = "\t".repeat(MIN_SIZE),
                charSequenceValue = "\t".repeat(MAX_SIZE),
            )
        )
    }

    @Test
    fun testEmptyInvalid() {
        val violations = validate(
            NotEmptySizeData(
                stringValue = "",
                charSequenceValue = "",
                mapValue = mapOf<Any, Any>(),
                collectionValue = listOf<Any>(),
                listValue = listOf<Any>(),
                setValue = setOf<Any>(),
                arrayValue = arrayOf<Any>(),
            )
        )

        violations.assertMessage("must not be empty")
            .presentFor("stringValue")
            .presentFor("charSequenceValue")
            .presentFor("mapValue")
            .presentFor("collectionValue")
            .presentFor("listValue")
            .presentFor("setValue")
            .presentFor("arrayValue")


        violations.assertMessage("must be of size min $MIN_SIZE and max $MAX_SIZE")
            .presentFor("stringValue")
            .presentFor("charSequenceValue")
            .presentFor("mapValue")
            .presentFor("collectionValue")
            .presentFor("listValue")
            .presentFor("setValue")
            .presentFor("arrayValue")
    }

    @Test
    fun testTooSmallInvalid() {
        val violations = validate(
            NotEmptySizeData(
                stringValue = "1",
                charSequenceValue = "1",
                mapValue = mapOf<Any, Any>(1 to 1),
                collectionValue = listOf<Any>(1),
                listValue = listOf<Any>(1),
                setValue = setOf<Any>(1),
                arrayValue = arrayOf<Any>(1),
            )
        )

        violations.assertMessage("must be of size min $MIN_SIZE and max $MAX_SIZE")
            .presentFor("stringValue")
            .presentFor("charSequenceValue")
            .presentFor("mapValue")
            .presentFor("collectionValue")
            .presentFor("listValue")
            .presentFor("setValue")
            .presentFor("arrayValue")
    }


    @Test
    fun testTooBigInvalid() {
        val violations = validate(
            NotEmptySizeData(
                stringValue = "1234",
                charSequenceValue = "1234",
                mapValue = mapOf<Any, Any>(1 to 1, 2 to 2, 3 to 3, 4 to 4),
                collectionValue = listOf<Any>(1, 2, 3, 4),
                listValue = listOf<Any>(1, 2, 3, 4),
                setValue = setOf<Any>(1, 2, 3, 4),
                arrayValue = arrayOf<Any>(1, 2, 3, 4),
            )
        )

        violations.assertMessage("must be of size min $MIN_SIZE and max $MAX_SIZE")
            .presentFor("stringValue")
            .presentFor("charSequenceValue")
            .presentFor("mapValue")
            .presentFor("collectionValue")
            .presentFor("listValue")
            .presentFor("setValue")
            .presentFor("arrayValue")
    }
}
package de.hsesslingen.keim.validazor

import de.hsesslingen.keim.validazor.testutils.NotNull
import de.hsesslingen.keim.validazor.testutils.ToStringEquals
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ConstraintInfoTest {

    @Test
    fun testFromAnnotationWithDetails() {
        val annotation = ToStringEquals(requiredValue = "something")
        val info = ConstraintInfo.fromAnnotation(annotation)

        assertEquals(annotation.annotationClass.java.name, info.id)
        assertNotNull(info.details)
        assertTrue(info.details.containsKey("requiredValue"))
        assertNotNull(info.details["requiredValue"])
        assertEquals("something", info.details["requiredValue"])
    }

    @Test
    fun testFromAnnotationWithoutDetails() {
        val annotation = NotNull()
        val info = ConstraintInfo.fromAnnotation(annotation)

        assertEquals(annotation.annotationClass.java.name, info.id)
        assertNotNull(info.details)
        assertTrue(info.details.isEmpty())
    }

}
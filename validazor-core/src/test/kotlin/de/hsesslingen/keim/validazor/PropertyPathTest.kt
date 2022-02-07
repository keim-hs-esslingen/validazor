package de.hsesslingen.keim.validazor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PropertyPathTest {

    fun testChildWithSeparator(root: PropertyPath, s: String) {
        val a = root.child("a")
        val b = root.child("b")

        val a_a = a.child("a")
        val a_b = a.child("b")
        val b_a = b.child("a")
        val b_b = b.child("b")

        assertEquals("a", a.toString())
        assertEquals("b", b.toString())

        assertEquals("a${s}a", a_a.toString())
        assertEquals("a${s}b", a_b.toString())
        assertEquals("b${s}a", b_a.toString())
        assertEquals("b${s}b", b_b.toString())
    }

    @Test
    fun testChildWithDefaultSeparator() {
        val s = PropertyPath.DEFAULT_PATH_SEPARATOR
        val root = PropertyPath.createRoot()
        testChildWithSeparator(root, s)
    }

    @Test
    fun testChildWithCustomSeparator() {
        val s = "|"
        val root = PropertyPath.createRoot(s)
        testChildWithSeparator(root, s)
    }

    @Test
    fun testChildWithLongCustomSeparator() {
        val s = "--->"
        val root = PropertyPath.createRoot(s)
        testChildWithSeparator(root, s)
    }

    @Test
    fun testIndex() {
        val s = PropertyPath.DEFAULT_PATH_SEPARATOR
        val root = PropertyPath.createRoot()

        val a = root.child("a")

        val a_0 = a.index(0)
        val a_2 = a.index(2)

        val a_2_b_x_1 = a_2.child("b").child("x").index(1)

        assertEquals("a[0]", a_0.toString())
        assertEquals("a[2]", a_2.toString())
        assertEquals("a[2]${s}b${s}x[1]", a_2_b_x_1.toString())
    }


    @Test
    fun testKey() {
        val root = PropertyPath.createRoot()

        val a = root.child("a")

        val a_0 = a.key(0)
        val a_kk = a.key("k")
        val a_kl = a.key(listOf("l"))

        assertEquals("a[0]", a_0.toString())
        assertEquals("a[\"k\"]", a_kk.toString())
        assertEquals("a[[l]]", a_kl.toString())
    }


    @Test
    fun testWhere() {
        val root = PropertyPath.createRoot()
        val a = root.child("a")

        val a_x5 = a.where("x", 5)
        val a_yz = a.where("y", "z")

        assertEquals("a[where x=5]", a_x5.toString())
        assertEquals("a[where y=\"z\"]", a_yz.toString())
    }
}
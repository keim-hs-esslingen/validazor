package de.hsesslingen.keim.validazor

import java.lang.reflect.Field
import java.lang.reflect.Modifier

fun Field.isStatic(): Boolean {
    return (this.modifiers and Modifier.STATIC) > 0
}

fun Field.isPrivate(): Boolean {
    return (this.modifiers and Modifier.PRIVATE) > 0
}

fun Field.isProtected(): Boolean {
    return (this.modifiers and Modifier.PROTECTED) > 0
}

fun Field.checkAndTrySetAccessible(obj: Any): Boolean {
    val accessObject = if (this.isStatic()) null else obj

    return if (this.canAccess(accessObject)) {
        true
    } else {
        this.trySetAccessible()
    }
}

/**
 * Extension function to turn any object into a [Map], by mapping its fields' names to their values.
 * Uses Java reflection to do so.
 */
private fun Any.asMapOfFields(): Map<String, Any?> {
    val map = HashMap<String, Any?>()

    this.javaClass.declaredFields
        .asSequence()
        .mapNotNull { if (it.checkAndTrySetAccessible(this)) it else null }
        .forEach { map[it.name] = it.get(this) }

    return map
}

/**
 * Creates a [ConstraintInfo] object from this annotation using [ConstraintInfo.fromAnnotation].
 */
fun Annotation.toConstraintInfo(): ConstraintInfo {
    return ConstraintInfo(
        id = this.javaClass.name,
        details = this.asMapOfFields()
    )
}
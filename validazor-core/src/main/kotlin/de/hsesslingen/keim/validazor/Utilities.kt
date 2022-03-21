package de.hsesslingen.keim.validazor

import java.lang.reflect.*

private fun Int.isStatic(): Boolean {
    return (this and Modifier.STATIC) > 0
}

private fun Int.isPrivate(): Boolean {
    return (this and Modifier.PRIVATE) > 0
}

private fun Int.isProtected(): Boolean {
    return (this and Modifier.PROTECTED) > 0
}

fun Field.isStatic(): Boolean {
    return this.modifiers.isStatic()
}

fun Field.isPrivate(): Boolean {
    return this.modifiers.isPrivate()
}

fun Field.isProtected(): Boolean {
    return this.modifiers.isProtected()
}

fun Method.isStatic(): Boolean {
    return this.modifiers.isStatic()
}

fun Method.isPrivate(): Boolean {
    return this.modifiers.isPrivate()
}

fun Method.isProtected(): Boolean {
    return this.modifiers.isProtected()
}

fun Field.checkAndTrySetAccessible(obj: Any): Boolean {
    val accessObject = if (this.isStatic()) null else obj

    return if (this.canAccess(accessObject)) {
        true
    } else {
        this.trySetAccessible()
    }
}

fun Method.checkAndTrySetAccessible(obj: Any): Boolean {
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
private fun Annotation.asMapOfFields(): Map<String, Any?> {
    val map = HashMap<String, Any?>()

    val methods = this.annotationClass.java.declaredMethods

    methods.asSequence()
        .filter { !it.isStatic() && !it.isPrivate() }
        .filter { it.parameterCount == 0 }
        .filter { it.returnType != Void.TYPE }
        .filter { it.checkAndTrySetAccessible(this) }
        .forEach { map[it.name] = it.invoke(this) }

    return map
}

/**
 * Creates a [ConstraintInfo] object from this annotation using [ConstraintInfo.fromAnnotation].
 */
fun Annotation.toConstraintInfo(): ConstraintInfo {
    return ConstraintInfo(
        id = this.annotationClass.qualifiedName ?: this.javaClass.name,
        details = this.asMapOfFields()
    )
}
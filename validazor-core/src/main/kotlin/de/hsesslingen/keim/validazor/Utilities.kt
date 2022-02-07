package de.hsesslingen.keim.validazor

/**
 * Extension function to turn any object into a [Map], by mapping its fields' names to their values.
 * Uses Java reflection to do so.
 */
fun Any.asMapOfFields(): Map<String, Any?> {
    return this::class.java.fields.groupBy({ it.name }, { it.get(this) })
}

/**
 * Creates a [ConstraintInfo] object from this annotation using [ConstraintInfo.fromAnnotation].
 */
fun Annotation.toConstraintInfo(): ConstraintInfo {
    return ConstraintInfo.fromAnnotation(this)
}
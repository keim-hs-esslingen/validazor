package de.hsesslingen.keim.validazor

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
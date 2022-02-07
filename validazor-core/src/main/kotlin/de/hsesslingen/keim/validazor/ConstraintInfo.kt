package de.hsesslingen.keim.validazor

/**
 * An object holding general information about a validation constraint.
 */
data class ConstraintInfo(
    /**
     * A unique identifier for the constraint, e.g. the full class name of the constraint annotation.
     */
    val id: String,

    /**
     * An optional map of detailed information about the constraint, e.g. a map of the annotations' properties.
     */
    val details: Map<String, Any?> = mapOf()
) {
    companion object {
        /**
         * Generates a [ConstraintInfo] instance from an annotation instance using reflection.
         * The id will be the full class name of the annotation, the details will be a map of
         * the annotations' fields mapped to their values.
         */
        @JvmStatic
        fun fromAnnotation(annotation: Annotation): ConstraintInfo {
            return annotation.toConstraintInfo()
        }
    }
}
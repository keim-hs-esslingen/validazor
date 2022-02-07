package de.hsesslingen.keim.validazor

/**
 * An interface used internally for collecting violations produced by the registered validators.
 */
interface ViolationCollector {
    /**
     * Adds a violation to this [ViolationCollector].
     */
    fun add(message: String, path: PropertyPath, constraintInfo: ConstraintInfo)
}

/**
 * An interface used internally for tracking the violations collected during validation.
 */
internal interface ViolationTracker : ViolationCollector {
    /**
     * `true` if any violations were added to this tracker, yet, otherwise `false`.
     */
    val hasViolations: Boolean
}
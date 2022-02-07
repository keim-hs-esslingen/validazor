package de.hsesslingen.keim.validazor

/**
 * A data class holding information about a violation of validation constraints.
 */
data class Violation(
    /**
     * The path to a (nested) invalid value of the tested object.
     * Path node separators can differ depending on the configuration of the validator.
     */
    val path: String,

    /**
     * A message describing what is wrong with the value.
     */
    val message: String,

    /**
     * An optional object holding more detailed information about the constraint that was violated.
     *
     * This object must not contain information that is specific to this violation but instead
     * general information about the violated constraint.
     */
    val constraint: ConstraintInfo? = null
)


package de.hsesslingen.keim.validazor

/**
 * Used to handle violations of validation constraints when validating using [Validazor.assertValid].
 */
class ViolationException(
    /**
     * The collection of violations leading to this [ViolationException].
     */
    val violations: Collection<Violation>,

    /**
     * A message for this exception. If none is specified, a message will be generated.
     *
     * If [violations] contains exactly one item, this message will contain
     * the [message][Violation.message] of this item. If [violations] contains multiple items,
     * this message will contain only the number of items in [violations].
     */
    message: String = when (violations.size) {
        0 -> throw IllegalArgumentException("When using ${ViolationException::class.simpleName} a non-empty list of violations must be provided. IF something else went wrong during validation, use a different exception class for this.")
        1 -> "A validation constraint has been violated: ${violations.iterator().next().message}"
        else -> "Multiple validation constraints were violated. (Total number of violations: ${violations.size})"
    }
) : Exception(message)
package de.hsesslingen.keim.validazor

/**
 * This interface must be implemented by custom constraint validators.
 * They can then be registered in a [Validazor.Builder] instance.
 *
 * [ConstraintValidazor]s should be implemented with thread safety in mind, so it is good practise
 * to implement them in an immutable and stateless fashion.
 *
 * If the [ConstraintValidazor] is supposed to be configurable, consider using a builder pattern to allow
 * configuration of such a [ConstraintValidazor], to be able to produce configured, immutable instances of
 * [ConstraintValidazor] from this builder.
 */
interface ConstraintValidazor<A : Annotation> {
    /**
     * This method is called during validation by a [Validazor] and is supposed to validate a given
     * [constraint] on the given [value].
     *
     * The [value] object resides somewhere in the hierarchy of the object that was sent into the validator for validation.
     * If the exact position is required for validation, the [path] argument can be used to determine it.
     * The [path] object also is required for adding violations to the [ViolationCollector][violations].
     *
     * This method is **NOT** supposed to recursively traverse the object tree of the [value] argument to find
     * more instances of its constraint using reflection. The validator does that and calls this function
     * for each constraint annotation it finds.
     *
     * To implement this method, usually a `null` check is done first. If [value] is `null`, this method should simply
     * return without adding any violations, unless having a non-`null` values is a required part of [constraint].
     *
     * After checking for null, usually a type check is performed on [value], using a `when` block in Kotlin or
     * `instanceof` in Java, to distinguish different methods of validating the [constraint] for different types.
     * It is totally OK to only implement validation for particular types, even just one, if the [constraint] is
     * only meant to be used with such types or does only make sense when used on these types.
     * If any other type is encountered than the expected one, this method **MUST NOT** add violations
     * to the [ViolationCollector][violations]. Instead, simply return **WITHOUT** adding any violations
     * to the [ViolationCollector][violations].
     *
     * If the validation of the [value] argument fails, a descriptive violation should be added to the [ViolationCollector][violations].
     * If [returnOnFirstViolation] is `true`, the method must return after any first violation was added.
     * If [returnOnFirstViolation] is `false`, the method should continue to find all violations on the instance it can
     * test for and add all of them to the [ViolationCollector][violations].
     *
     * Ultimately, the collected violations are used to determine the final result of the root object that is being validated.
     * If any violations are there, the object is considered invalid.
     *
     * @param constraint The constraint annotation instance used to annotate the field whose value is being tested.
     * @param value The value of the field that was annotated using the annotation that this [ConstraintValidazor] is for.
     * @param path The path of the value in the hierarchy of the root object that was sent to a [Validazor] for validation.
     * @param violations An object usable for simple collection of violations.
     * @param returnOnFirstViolation Whether the method should return after finding a first violation.
     */
    fun validate(
        constraint: A,
        value: Any?,
        path: PropertyPath,
        violations: ViolationCollector,
        returnOnFirstViolation: Boolean,
    )
}
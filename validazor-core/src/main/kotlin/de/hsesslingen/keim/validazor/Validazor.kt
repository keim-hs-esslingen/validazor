package de.hsesslingen.keim.validazor

import de.hsesslingen.keim.validazor.Validazor.Builder
import java.lang.reflect.Field

/**
 * This class is the heart of this validation framework. It contains the recursive traversal algorithm that searches
 * for annotations on objects and validates them if an appropriate validator is registered.
 *
 * To reduce side effects and increase thread safety, this class is designed with an immutable interface.
 * Configure and create instances of this class by instantiating the nested [Builder] class.
 *
 * Thread safety cannot be guaranteed though since the [ConstraintValidazor] instances used in this class
 * can be provided by third parties and therefore are out of our control. If these [ConstraintValidazor]s are
 * thread safe though, concurrently calling the same [Validazor] instance with multiple threads is safe.
 *
 * An instance of [Validazor] can therefore be used with any injection framework of choice.
 *
 * @see [Builder] for more information on the particular properties.
 */
class Validazor private constructor(
    /**
     * @see [Builder.pathSeparator]
     */
    val pathSeparator: String,

    /**
     * @see [Builder.excludeStaticFields]
     */
    val excludeStaticFields: Boolean,

    /**
     * @see [Builder.excludePrivateFields]
     */
    val excludePrivateFields: Boolean,

    /**
     * @see [Builder.excludeProtectedFields]
     */
    val excludeProtectedFields: Boolean,

    /**
     * @see [Builder.stopOnFirstViolation]
     */
    val stopOnFirstViolation: Boolean,

    /**
     * @see [Builder.excludePackageByPrefix]
     */
    val excludedPackagesPrefixes: List<String>,

    /**
     * A [Map] of validators for validating constraints. @see [Builder.register] for more information.
     */
    val validators: Map<Class<*>, ConstraintValidazor<*>>
) {
    private fun <A : Annotation> getValidatorFor(constraint: A): ConstraintValidazor<A>? {
        @Suppress("UNCHECKED_CAST")
        // The unchecked cast below is safe as long as the validator-class-mappings are correct.
        // Therefore, the constructor is set to private so the usage of a Builder is enforced, which
        // checks the types in its registration methods.
        return validators[constraint.annotationClass.java] as ConstraintValidazor<A>?
    }

    /**
     * Validates the given [instance] using the registered [ConstraintValidazor]s.
     *
     * If the resulting [Set] of [Violation]s is empty, [instance] can be considered valid.
     * If the resulting [Set] of [Violation]s is *not* empty, [instance] must be considered invalid.
     */
    fun validate(instance: Any): List<Violation> {
        val violations = ArrayList<Violation>()

        val tracker = object : ViolationTracker {
            override val hasViolations: Boolean
                get() = violations.isNotEmpty()

            override fun add(message: String, path: PropertyPath, constraintInfo: ConstraintInfo) {
                violations.add(Violation(path.toString(), message, constraintInfo))
            }
        }

        val rootPath = PropertyPath.createRoot(pathSeparator = pathSeparator)

        validate(instance, rootPath, tracker)

        return violations
    }

    /**
     * Validates the given [instance] using the registered [ConstraintValidazor]s.
     *
     * If [instance] is invalid, a [ViolationException] is thrown, containing all the [Violation]s found.
     *
     * @throws ViolationException if instance is invalid.
     */
    @Throws(ViolationException::class)
    fun assertValid(instance: Any) {
        val violations = validate(instance)

        if (violations.isNotEmpty()) {
            throw ViolationException(violations)
        }
    }

    /**
     * Validates the given [instance] using the registered [ConstraintValidazor]s.
     *
     * @return `true` if [instance] is valid, `false` if [instance] is invalid.
     */
    fun isValid(instance: Any): Boolean {
        val violations = validate(instance)
        return violations.isEmpty()
    }

    private fun validate(
        instance: Any?,
        instancePath: PropertyPath,
        tracker: ViolationTracker
    ): Boolean {
        if (instance == null) {
            return true
        }

        // Check whether the current object is an object from the Java stdlib or the Kotlin stdlib.
        // These objects to not contain constraint annotations and are excluded from validation checks
        // to increase performance and to avoid problems due to accessing deep hidden stuff.
        val packageName = instance.javaClass.packageName
        val isStdlibClass = packageName.startsWith("java.") || packageName.startsWith("kotlin.")

        if (isStdlibClass || excludedPackagesPrefixes.any { packageName.startsWith(it) }) {
            return true
        }

        when (instance) {
            is Map<*, *> -> instance.keys
                .all { key ->
                    (validate(key, instancePath.child("keys").key(key), tracker) || !stopOnFirstViolation)
                            && (validate(instance[key], instancePath.key(key), tracker) || !stopOnFirstViolation)
                }

            is Iterable<*> -> instance.asSequence()
                .mapIndexed { index, el -> validate(el, instancePath.index(index), tracker) }
                .all { isValid -> isValid || !stopOnFirstViolation }

            else -> validateObject(instance, instancePath, tracker)
        }

        return tracker.hasViolations
    }

    private fun validateObject(
        instance: Any,
        instancePath: PropertyPath,
        tracker: ViolationTracker
    ): Boolean {
        var currentClass: Class<*>? = instance.javaClass

        val classList = generateSequence { currentClass }
            .onEach { currentClass = it.superclass }
            .takeWhile {
                @Suppress("SENSELESS_COMPARISON") // Can indeed be null according to JavaDoc of getSuperclass()
                it != null
            }
            .toList()

        // First validate class level annotations
        classList.asSequence()
            .flatMap { it.annotations.asSequence() }
            .all { validateValue(instance, instancePath, tracker, it) || !stopOnFirstViolation }

        if (tracker.hasViolations && stopOnFirstViolation) {
            return false
        }

        // Then field level annotations
        classList.asSequence()
            .flatMap { it.declaredFields.asSequence() }
            .all {
                validateField(instance, it, instancePath, tracker) || !stopOnFirstViolation
            }

        return tracker.hasViolations
    }

    private fun <T : Any> validateField(
        instance: T,
        property: Field,
        instancePath: PropertyPath,
        tracker: ViolationTracker,
    ): Boolean {
        if (excludeStaticFields && property.isStatic()) {
            return true
        }
        if (excludePrivateFields && property.isPrivate()) {
            return true
        }
        if (excludeProtectedFields && property.isProtected()) {
            return true
        }

        if (property.isPrivate() || property.isProtected()) {
            if (!property.checkAndTrySetAccessible(instance)) {
                return true
            }
        }

        val fieldValue: Any? = property.get(instance)
        val fieldPath = instancePath.child(property.name)

        // Validate each annotation on the field.
        property.declaredAnnotations.all {
            // Using all together with || !stopOnFirstViolation allows an early exit if stopping is configured
            // and a continuation if stopping is not configured.
            validateValue(fieldValue, fieldPath, tracker, it) || !stopOnFirstViolation
        }

        if (tracker.hasViolations && stopOnFirstViolation) {
            return false
        }

        if (fieldValue != null) {
            // Recursively call validation function on every child property.
            return validate(fieldValue, fieldPath, tracker)
        }

        return true
    }

    private fun validateValue(
        value: Any?,
        valuePath: PropertyPath,
        tracker: ViolationTracker,
        constraint: Annotation
    ): Boolean {
        getValidatorFor(constraint)?.validate(constraint, value, valuePath, tracker, stopOnFirstViolation)
        return !tracker.hasViolations || !stopOnFirstViolation
    }

    /**
     * A builder class for configuring [Validazor] instances.
     */
    class Builder(
        /**
         * @see [pathSeparator]
         */
        var pathSeparator: String = PropertyPath.DEFAULT_PATH_SEPARATOR,
        /**
         * @see [excludeStaticFields]
         */
        var excludeStaticFields: Boolean = true,
        /**
         * @see [excludePrivateFields]
         */
        var excludePrivateFields: Boolean = false,
        /**
         * @see [excludeProtectedFields]
         */
        var excludeProtectedFields: Boolean = false,
        /**
         * @see [excludePackageByPrefix]
         */
        excludedPackagesPrefixes: List<String> = listOf(),
        /**
         * @see [stopOnFirstViolation]
         */
        var stopOnFirstViolation: Boolean = false,
    ) {
        private val registeredValidators = HashMap<Class<*>, ConstraintValidazor<*>>()
        private val excludedPackagesPrefixes = excludedPackagesPrefixes.toMutableList()

        /**
         * Which path node separator should be used when converting the location of a property to a string path.
         */
        fun pathSeparator(value: String): Builder {
            pathSeparator = value
            return this
        }

        /**
         * Whether static fields should be ignored during validation. Default value is `true`.
         */
        fun excludeStaticFields(value: Boolean): Builder {
            excludeStaticFields = value
            return this
        }

        /**
         * Whether private fields should be ignored during validation. Default value is `false`.
         */
        fun excludePrivateFields(value: Boolean): Builder {
            excludePrivateFields = value
            return this
        }

        /**
         * Whether protected fields should be ignored during validation. Default value is `false`.
         */
        fun excludeProtectedFields(value: Boolean): Builder {
            excludeProtectedFields = value
            return this
        }

        /**
         * Whether validation should stop on the first violation it finds. Default value is `false`.
         *
         * If set to `false`, the validation will continue after encountering violations to find all violations
         * in the object that is being tested.
         */
        fun stopOnFirstViolation(value: Boolean): Builder {
            stopOnFirstViolation = value
            return this
        }

        /**
         * A list of prefixes of packages excluded from validation.
         * Objects of classes inside these packages are excluded from validation.
         */
        fun excludePackageByPrefix(prefix: String): Builder {
            excludedPackagesPrefixes.add(prefix)
            return this
        }

        /**
         * Registers a new constraint annotation together with a validator on this [Builder].
         *
         * This enables [Validazor]s built using this [Builder] to validate such constraints.
         *
         * @param constraintClass The class of the constraint annotation.
         * @param validator A validator instance able to validate the given constraint class.         *
         */
        fun <A : Annotation> register(constraintClass: Class<A>, validator: ConstraintValidazor<A>): Builder {
            registeredValidators[constraintClass] = validator
            return this
        }

        /**
         * Convenience method for Kotlin users for [Builder.register] using reified type.
         */
        inline fun <reified A : Annotation> register(validator: ConstraintValidazor<A>): Builder {
            // Convenience function for kotlin users. Omits explicit declaration of class in function call.
            return register(A::class.java, validator)
        }

        /**
         * Registers a [ValidazorModule] on this builder that can alter the configuration of this [Builder] and therefore
         * also the configuration of [Validazor]s built using this [Builder].
         *
         * A module can also be used to group a set of [ConstraintValidazor]s and register them all
         * at this [Builder] inside the module implementation.
         */
        fun register(module: ValidazorModule): Builder {
            module.configure(this)
            return this
        }

        /**
         * Build an immutable [Validazor] instance that can be used to validate arbitrary objects.
         */
        fun build(): Validazor {
            return Validazor(
                pathSeparator = pathSeparator,
                excludeStaticFields = excludeStaticFields,
                excludePrivateFields = excludePrivateFields,
                excludeProtectedFields = excludeProtectedFields,
                stopOnFirstViolation = stopOnFirstViolation,
                excludedPackagesPrefixes = excludedPackagesPrefixes,
                validators = registeredValidators,
            )
        }
    }
}



package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.*
import de.hsesslingen.keim.validazor.constraints.Password.CharacterKind
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.round

/**
 * A constraint that can be used to validate password requirements on passwords.
 *
 * This is e.g. useful for an API that is used for (re-)setting passwords.
 */
@Target(FIELD, CLASS)
annotation class Password(
    /**
     * The minimum length required for the password. Default is `0`.
     */
    val minLength: Int = 0,

    /**
     * The maximum length allowed for the password. Default is [Int.MAX_VALUE].
     */
    val maxLength: Int = Int.MAX_VALUE,

    /**
     * A set of characters which are allowed exclusively in the password.
     * Default is an empty array, allowing any character.
     */
    val allowedCharacters: CharArray = [], // Any chars allowed.

    /**
     * A set of characters which are forbidden in the password.
     * Default is an empty array, allowing any character.
     */
    val forbiddenCharacters: CharArray = [], // None forbidden.

    /**
     * A set of [CharacterKind]s which must all be represented by at least one character in the password.
     * Default is an empty array, which sets no requirements on the kinds of characters used in passwords.
     */
    val requiredCharacterKinds: Array<CharacterKind> = [], // None required

    /**
     * A minimum amount of entropy that must be present in the password.
     * Default value is `0.0`, allowing any complexity for passwords.
     *
     * The entropy in the password is estimated based on the length and the variety of characters used in it.
     * Both, increasing the length and adding more [CharacterKind]s or different characters in general, will
     * increase the entropy of the password.
     */
    val minEntropyBits: Double = 0.0,
) {
    enum class CharacterKind(
        val approximateSetSize: Int
    ) {
        LOWERCASE_LETTERS(26),
        UPPERCASE_LETTERS(26),
        DIGITS(10),
        SPECIAL_CHARACTERS(12);

        fun matches(char: Char): Boolean {
            return when (this) {
                LOWERCASE_LETTERS -> char.isLowerCase()
                UPPERCASE_LETTERS -> char.isUpperCase()
                DIGITS -> char.isDigit()
                SPECIAL_CHARACTERS -> !char.isLetterOrDigit()
            }
        }
    }

    /**
     * A [ConstraintValidator] for the [Password] constraint annotation.
     */
    class Validator : ConstraintValidator<Password>, ValidazorModule {
        override fun validate(
            constraint: Password,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
            now: NowContext,
        ) {
            when (value) {
                null -> {}
                is String -> validateOnCharArray(
                    constraint,
                    value.toCharArray(),
                    path,
                    violations,
                    returnOnFirstViolation
                )
                is CharSequence -> validateOnCharArray(
                    constraint,
                    value.toList().toCharArray(),
                    path,
                    violations,
                    returnOnFirstViolation
                )
                is CharArray -> validateOnCharArray(constraint, value, path, violations, returnOnFirstViolation)
                else -> {}
            }
        }

        private val Char.characterKind: CharacterKind
            get() {
                return when {
                    this.isLowerCase() -> CharacterKind.LOWERCASE_LETTERS
                    this.isUpperCase() -> CharacterKind.UPPERCASE_LETTERS
                    this.isDigit() -> CharacterKind.DIGITS
                    else -> CharacterKind.SPECIAL_CHARACTERS
                }
            }

        private fun validateOnCharArray(
            constraint: Password,
            password: CharArray,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
        ) {
            if (password.size < constraint.minLength) {
                violations.add(
                    "must be at least ${constraint.minLength} characters long",
                    path,
                    constraint.toConstraintInfo()
                )
                if (returnOnFirstViolation) return
            }

            if (password.size > constraint.maxLength) {
                violations.add(
                    "must not be longer than ${constraint.maxLength} characters",
                    path,
                    constraint.toConstraintInfo()
                )
                if (returnOnFirstViolation) return
            }

            if (constraint.allowedCharacters.isNotEmpty()) {
                for (char in password) {
                    if (!constraint.allowedCharacters.contains(char)) {
                        violations.add("must not contain character '$char'", path, constraint.toConstraintInfo())
                        if (returnOnFirstViolation) return
                    }
                }
            }

            if (constraint.forbiddenCharacters.isNotEmpty()) {
                for (char in password) {
                    if (constraint.forbiddenCharacters.contains(char)) {
                        violations.add("must not contain character '$char'", path, constraint.toConstraintInfo())
                        if (returnOnFirstViolation) return
                    }
                }
            }

            if (constraint.requiredCharacterKinds.isNotEmpty()) {
                for (kind in constraint.requiredCharacterKinds) {

                    if (password.none { c -> kind.matches(c) }) {
                        val kindName = kind.name.lowercase().replace("_", " ")

                        violations.add(
                            "must contain characters of kind \"$kindName\"",
                            path,
                            constraint.toConstraintInfo()
                        )
                        if (returnOnFirstViolation) return
                    }

                }
            }

            if (constraint.minEntropyBits > 0) {
                val possibleCombinations = if (password.isEmpty()) {
                    1.0
                } else {
                    val numOfDistinctSpecialChars = password.asSequence()
                        .filter { CharacterKind.SPECIAL_CHARACTERS.matches(it) }
                        .distinct()
                        .count()

                    val totalSetSize = password.asSequence()
                        .map { it.characterKind }
                        .distinct()
                        .filter { it != CharacterKind.SPECIAL_CHARACTERS } // Ignore special chars set size
                        .sumOf { it.approximateSetSize } + numOfDistinctSpecialChars // Add exact number of distinct special chars instead.

                    totalSetSize.toDouble()
                        .pow(password.size) // Results in Double.POSITIVE_INFINITY on overflow.
                        .coerceAtLeast(1.0) // Should never be necessary, but for safety we keep it here.
                }

                // If we have a "finite" number of combinations, calculate the entropy bits and validate them.
                if (possibleCombinations < Double.POSITIVE_INFINITY) {
                    val estimatedEntropyBits = log(possibleCombinations, 2.0)
                    // Round value for readability. Since it's only an estimation, using the exact value is not required.
                    val rounded = round(estimatedEntropyBits * 10.0) / 10.0

                    if (rounded < constraint.minEntropyBits) {
                        violations.add(
                            "must have at least ${constraint.minEntropyBits} bits of entropy (estimated bits of entropy: $rounded)",
                            path,
                            constraint.toConstraintInfo()
                        )
                        if (returnOnFirstViolation) return
                    }
                }
            }
        }

        override fun configure(builder: de.hsesslingen.keim.validazor.Validazor.Builder) {
            builder.register(Password::class.java, this)
        }
    }
}






package de.hsesslingen.keim.validazor

import de.hsesslingen.keim.validazor.constraints.jakarta.JakartaValidationModule

/**
 * Registers all the default validators, including the common constraints and the Jakarta constraints.
 */
fun Validazor.Builder.registerDefaultValidators(): Validazor.Builder {
    this.register(de.hsesslingen.keim.validazor.constraints.Password.Validator())
    this.register(JakartaValidationModule())
    return this
}

/**
 * Registers all the default validators, including the common constraints and the Jakarta constraints.
 */
class DefaultValidators : ValidazorModule {
    override fun configure(builder: Validazor.Builder) {
        builder.registerDefaultValidators()
    }

    companion object {
        private val DEFAULT_VALIDATOR: Validazor by lazy {
            Validazor.Builder()
                .registerDefaultValidators()
                .build()
        }

        /**
         * Returns a [Validazor] in the default configuration.
         */
        @JvmStatic
        fun getDefaultValidator(): Validazor {
            return DEFAULT_VALIDATOR
        }
    }
}
package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.ValidazorModule
import de.hsesslingen.keim.validazor.Validazor

fun Validazor.Builder.registerJakartaConstraints(): Validazor.Builder {
    this.register(AssertFalseValidator())
        .register(AssertTrueValidator())
        .register(DecimalMaxValidator())
        .register(DecimalMinValidator())
        .register(DigitsValidator())
        .register(EmailValidator())
        .register(FutureValidator())
        .register(FutureOrPresentValidator())
        .register(MaxValidator())
        .register(MinValidator())
        .register(NegativeValidator())
        .register(NegativeOrZeroValidator())
        .register(NotBlankValidator())
        .register(NotEmptyValidator())
        .register(NotNullValidator())
        .register(NullValidator())
        .register(PastValidator())
        .register(PastOrPresentValidator())
        .register(PatternValidator())
        .register(PositiveValidator())
        .register(PositiveOrZeroValidator())
        .register(SizeValidator())

    return this
}

class JakartaValidationModule : ValidazorModule {
    override fun configure(builder: Validazor.Builder) {
        builder.registerJakartaConstraints()
    }

    companion object {
        /**
         * Takes an instance of [Validazor.Builder] and configures it to contain all the Jakarta [ConstraintValidator]s.
         */
        @JvmStatic
        fun asModule(builder: Validazor.Builder) {
            builder.registerJakartaConstraints()
        }
    }
}
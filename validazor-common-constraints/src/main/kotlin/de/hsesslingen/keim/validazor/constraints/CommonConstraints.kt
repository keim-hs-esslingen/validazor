package de.hsesslingen.keim.validazor.constraints

import de.hsesslingen.keim.validazor.ConstraintValidator
import de.hsesslingen.keim.validazor.Validazor
import de.hsesslingen.keim.validazor.ValidazorModule

fun Validazor.Builder.registerCommonConstraints(): Validazor.Builder {
    this
        .register(AssertTrue.Validator())
        .register(AssertFalse.Validator())
        .register(DecimalMax.Validator())
        .register(DecimalMin.Validator())
        .register(Digits.Validator())
        .register(Email.Validator())
        .register(Future.Validator())
        .register(FutureOrPresent.Validator())
        .register(Max.Validator())
        .register(Min.Validator())
        .register(Negative.Validator())
        .register(NegativeOrZero.Validator())
        .register(NotBlank.Validator())
        .register(NotEmpty.Validator())
        .register(NotNull.Validator())
        .register(Null.Validator())
        .register(Password.Validator())
        .register(Past.Validator())
        .register(PastOrPresent.Validator())
        .register(Pattern.Validator())
        .register(Positive.Validator())
        .register(PositiveOrZero.Validator())
        .register(Size.Validator())

    return this
}

class CommonConstraints : ValidazorModule {
    override fun configure(builder: Validazor.Builder) {
        builder.registerCommonConstraints()
    }

    companion object {
        /**
         * Takes an instance of [Validazor.Builder] and configures it to contain all the common [ConstraintValidator]s.
         */
        @JvmStatic
        fun asModule(builder: Validazor.Builder) {
            builder.registerCommonConstraints()
        }
    }
}
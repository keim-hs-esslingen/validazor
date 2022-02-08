package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ValidazorModule
import de.hsesslingen.keim.validazor.Validazor

class JakartaValidationModule : ValidazorModule {
    override fun configure(builder: Validazor.Builder) {
        builder.register(AssertFalseValidator())
        builder.register(AssertTrueValidator())
        builder.register(DecimalMaxValidazor())
        builder.register(DecimalMinValidazor())
        builder.register(DigitsValidazor())
        builder.register(EmailValidazor())
        builder.register(FutureValidazor())
        builder.register(FutureOrPresentValidazor())
        builder.register(MaxValidazor())
        builder.register(MinValidazor())
        builder.register(NegativeValidazor())
        builder.register(NegativeOrZeroValidazor())
        builder.register(NotBlankValidazor())
        builder.register(NotEmptyValidazor())
        builder.register(NotNullValidazor())
        builder.register(NullValidazor())
        builder.register(PastValidazor())
        builder.register(PastOrPresentValidazor())
        builder.register(PatternValidazor())
        builder.register(PositiveValidazor())
        builder.register(PositiveOrZeroValidazor())
        builder.register(SizeValidazor())
    }
}
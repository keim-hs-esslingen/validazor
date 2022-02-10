package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.ValidazorModule
import de.hsesslingen.keim.validazor.Validazor

class JakartaValidationModule : ValidazorModule {
    override fun configure(builder: Validazor.Builder) {
        builder.register(AssertFalseValidator())
        builder.register(AssertTrueValidator())
        builder.register(DecimalMaxValidator())
        builder.register(DecimalMinValidator())
        builder.register(DigitsValidator())
        builder.register(EmailValidator())
        builder.register(FutureValidator())
        builder.register(FutureOrPresentValidator())
        builder.register(MaxValidator())
        builder.register(MinValidator())
        builder.register(NegativeValidator())
        builder.register(NegativeOrZeroValidator())
        builder.register(NotBlankValidator())
        builder.register(NotEmptyValidator())
        builder.register(NotNullValidator())
        builder.register(NullValidator())
        builder.register(PastValidator())
        builder.register(PastOrPresentValidator())
        builder.register(PatternValidator())
        builder.register(PositiveValidator())
        builder.register(PositiveOrZeroValidator())
        builder.register(SizeValidator())
    }
}
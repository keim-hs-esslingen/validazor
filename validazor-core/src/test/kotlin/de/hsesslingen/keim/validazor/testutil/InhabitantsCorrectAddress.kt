package de.hsesslingen.keim.validazor.testutil

import de.hsesslingen.keim.validazor.ConstraintValidazor
import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector

annotation class InhabitantsCorrectAddress {
    class Validator : ConstraintValidazor<InhabitantsCorrectAddress> {
        override fun validate(
            constraint: InhabitantsCorrectAddress,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean
        ) {
            when (value) {
                null -> {}
                is Address -> {
                    val inhabitants = value.inhabitants

                    val addressesAreCorrect = inhabitants.isNullOrEmpty() || inhabitants.all {
                        val address = it.address
                        address != null
                                && address.street == value.street
                                && address.city == value.city
                                && address.zip == value.zip
                    }

                    if (!addressesAreCorrect) {
                        violations.add(
                            MSG,
                            path,
                            constraint
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val MSG = "must not contain inhabitants with address different than this one"
    }
}
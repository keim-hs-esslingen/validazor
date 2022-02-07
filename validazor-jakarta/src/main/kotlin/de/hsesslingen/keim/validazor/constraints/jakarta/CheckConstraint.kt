package de.hsesslingen.keim.validazor.constraints.jakarta

import de.hsesslingen.keim.validazor.PropertyPath
import de.hsesslingen.keim.validazor.ViolationCollector
import de.hsesslingen.keim.validazor.toConstraintInfo

internal inline fun checkConstraint(
    violationMessage: String,
    path: PropertyPath,
    constraint: Annotation,
    violations: ViolationCollector,
    checkFunc: () -> Boolean
): Boolean {
    val isOk = checkFunc()

    if (!isOk) {
        violations.add(violationMessage, path, constraint.toConstraintInfo())
    }

    return isOk
}
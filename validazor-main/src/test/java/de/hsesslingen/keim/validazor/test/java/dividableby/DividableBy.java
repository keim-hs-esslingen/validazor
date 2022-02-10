package de.hsesslingen.keim.validazor.test.java.dividableby;

import de.hsesslingen.keim.validazor.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DividableBy {

    // The value by which the number of interest must be dividable.
    long value() default 1;

    // Using a static inner class for the implementation of the validator.
    class Validator implements ConstraintValidator<DividableBy> {

        @Override
        public void validate(
                // The particular instance of your constraint.
                @NotNull DividableBy constraint,
                // The value that must be tested.
                @Nullable Object value,
                // The path to the object. Only needed for adding violations.
                @NotNull PropertyPath path,
                // A simple collector for violations.
                @NotNull ViolationCollector violations,
                // Whether to return after finding a first violation.
                // Only needed if multiple violations can be found with one constraint.
                boolean returnOnFirstViolation,
                // A reference for the current moment in time for validation, provided in various types.
                @NotNull NowContext now
        ) {
            // null values usually are considered valid. Nothing to do.
            if (value == null) {
                return;
            }

            long remainder = 0;

            // Check supported types and calculate remainder.
            if (value instanceof Integer) {
                remainder = ((Integer) value) % constraint.value();
            } else if (value instanceof Long) {
                remainder = ((Long) value) % constraint.value();
            }

            // Test if remainder is not zero.
            if (remainder != 0) {
                // Upon violations, add a violation using a descriptive message, the path and a constraint info
                // object, either self-made or generated as seen here.
                violations.add(
                        "must be dividable by " + constraint.value(),
                        path,
                        ConstraintInfo.fromAnnotation(constraint)
                );
            }
        }
    }
}

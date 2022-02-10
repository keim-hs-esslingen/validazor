# Validazor

A library for the JVM written in Kotlin for validating objects using annotations. It works similar to the Jakarta
validation API but is still different. Some aspects of Validazor that should be highlighted:

- **Very simple to use!**
- **Very simple to integrate!**
- **Works with your injection framework of choice!**
- **Thread safe usage!**
- **Easily extensible!**
- **Easy to configure!**
- **Validates Jakarta-Validation-Constraints out of the box!**

Validazor is still very young and evolving. We appreciate your feedback and feature requests on the issues-page here on
GitHub. Feel free to open an issue there.

---

## Table of Contents:

1. [Dependency](#dependency)
    1. Maven
    2. Gradle
2. [Usage](#usage)
    1. [Annotate Classes](#annotate-classes)
    2. [Obtain Validazor](#obtain-validazor-instance)
3. [Custom constraints](#custom-constraints)
    1. [Implement Constraint](#implement-constraint)
    2. [Register ConstraintValidator](#register-constraintvalidator)

---

## Dependency:

The library is available at the maven central repository.

**Maven:**

```xml

<dependencies>
    <dependency>
        <groupId>com.github.keim-hs-esslingen</groupId>
        <artifactId>validazor</artifactId>
        <version>@{validazorVersion}</version>
    </dependency>
</dependencies>
```

**Gradle:**

```kotlin
implementation("com.github.keim-hs-esslingen.validazor:validazor:$validazorVersion")
```

You can find the latest version on the release page.

---

## Usage:

Annotate classes that should be validated with any of the Jakarta validation API constraint annotations or any of the
common constraints provided by Validazor.

### Annotate classes:

**Kotlin:**

```Kotlin
data class Person(
    @field:NotBlank
    @field:NotNull
    val name: String,

    @field:PositiveOrZero
    val age: Int,

    @field:NotBlank
    @field:Email
    val email: String,
)
```

**Note:** If using Kotlin, the annotations must be specialized to be put on the fields explicitly. We are aiming to
remove this restriction in the future.

**Java:**

```java
class Person {
    @NotBlank
    @NotNull
    String name;

    @PositiveOrZero
    Integer age;

    @NotBlank
    @Email
    String email;
}
```

If using Java, annotations on getters and setters are not yet supported. We are aiming to remove this restriction in the
future.

### Obtain `Validazor` instance

Obtain an instance of `Validazor` to validate an annotated object.

**Kotlin:**

```Kotlin
import de.hsesslingen.keim.validazor.DefaultValidators.*

fun test() {
    val person = Person("Ben", 29, "anonymous@hs-esslingen.de")
    val validator = getDefaultValidator()
    val violations = validator.validate(person) // returns List<Violation>
    println(violations)
}
```

**Java:**

```java
import static de.hsesslingen.keim.validazor.DefaultValidators.getDefaultValidator;

class TestValidazor {
    void test() {
        Person person = new Person("Ben", 29, "anonymous@hs-esslingen.de");
        Validazor validator = getDefaultValidator();
        List<Violation> violations = validator.validate(person);
        System.out.println(violations);
    }
}
```

If the returned list of violations is empty, the tested object can be considered valid. If the list is not empty, the
tested object *must* be considered invalid. In this case the violation items in the list provide further information
about what is wrong with the object.

---

## Custom Constraints:

Extending Validazor with own constraints is very easy.

1. Create an annotation class with all the properties needed for validation.
2. Create a class implementing the `ConstraintValidator<A>` interface with `A` being the type of your custom constraint
   annotation.
1. Register an instance of your validator at a `Validazor.Builder` instance and build a validator from it.

Need an example? Here you go:

### Implement Constraint:

Let's implement a constraint that checks if an integer or long is dividable by a particular value.

**Kotlin:**

```kotlin
annotation class DividableBy(
    val value: Long = 1
) {
    // We put the validator implementation right here because they belong together.
    class Validator : ConstraintValidator<DividableBy> {

        override fun validate(
            // The particular instance of your constraint.
            constraint: DividableBy,
            // The value that must be tested.
            value: Any?,
            // The path to the object. Only needed for adding violations.
            path: PropertyPath,
            // A simple collector for violations.
            violations: ViolationCollector,
            // Whether to return after finding a first violation.
            // Only needed if multiple violations can be found with one constraint.
            returnOnFirstViolation: Boolean,
            // A reference for the current moment in time for validation, provided in various types.
            // We don't need this here, but its part of the interface.
            now: NowContext
        ) {
            // Calculate remainder on known types.
            val remainder = when (value) {
                null -> return // null values usually are considered valid. Nothing to do.
                is Int -> value % constraint.value
                is Long -> value % constraint.value
                else -> return // Other types not supported. Nothing to do.
            }

            // Test if remainder is not zero.
            if (remainder != 0L) {
                // Upon violations, add a violation using a descriptive message,
                // the path and a constraint info object, either self-made or
                // generated using the handy extension function for Kotlin users.
                violations.add(
                    "must be dividable by " + constraint.value,
                    path,
                    constraint.toConstraintInfo()
                )
            }
        }
    }
}
```

**Java:**

```java

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
                // We don't need this here, but its part of the interface.
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
                // Upon violations, add a violation using a descriptive message,
                // the path and a constraint info object, either self-made
                // or generated as seen here.
                violations.add(
                        "must be dividable by " + constraint.value(),
                        path,
                        ConstraintInfo.fromAnnotation(constraint)
                );
            }
        }
    }
}
```

### Register `ConstraintValidator`

Register your custom constraint together with it's validator as follows:

**Kotlin:**

```kotlin
fun register() {
    val validazor = Validazor.Builder()
        .register(DividableBy.Validator())
        .build()

    // use it...
}
```

**Java:**

```java
class Whatever {
    void register() {
        var validazor = new Validazor.Builder()
                .register(DividableBy.class, new DividableBy.Validator())
                .build();

        // use it...
    }
}
```

This step is supposed to be done on startup of the application, because the retrieved Validazor is stateless and can be
reused. Provide it to your injection framework of choice.


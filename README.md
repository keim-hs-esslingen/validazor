# Validazor

A library for the JVM written in Kotlin for validating objects using annotations. It works similar to the Jakarta
validation API but is still different. Some aspects of Validazor that should be highlighted:

- **Very simple to use!**
- **Very simple to integrate!**
- **Works with your injection framework of choice!**
- **Thread safe usage!**
- **Easy to extend!**
- **Easy to configure!**
- **Support for Jakarta-Validation-Constraints!**

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
4. [Jakarta-Constraint-Support](#jakarta-constraint-support)
    1. [Registration of Module](#registration-of-jakartamodule)
    2. [Important for Kotlin users](#important-for-kotlin-users-of-jakarta-constraints-with-validazor)
5. [General Drawbacks](#general-drawbacks)

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

Annotate classes that should be validated with any of the existing common constraints available by default. These are:

- `AssertTrue`: Validates that a value is `true`.
- `AssertFalse`: Validates that a value is `false`.
- `Null`: Validates that a value is `null`.
- `NotNull`: Validates that a value is *not* `null`.
- `NotEmpty`: Validates that a value is *not* empty.
- `NotBlank`: Validates that a value is *not* blank.
- `Min`: Validates that a number does not undermatch a minimum.
- `Max`: Validates that a number does not exceed a maximum.
- `DecimalMin`: Validates that a numerical value does not undermatch a minimum. Supports `String`.
- `DecimalMax`: Validates that a numerical values does not exceed a maximum. Supports `String`.
- `Digits`: Validates that the number of integer and fractional digits of a numerical value does not exceed a maximum.
- `Positive`: Validates that a number is greater than zero.
- `PositiveOrZero`: Validates that a number is greater than or equal to zero.
- `Negative`: Validates that a number is less than zero.
- `NegativeOrZero`: Validates that a number is less than or equal to zero.
- `Future`: Validates that a temporal value is in the future.
- `FutureOrPresent`: Validates that a temporal value in the future or present.
- `Past`: Validates that a temporal value is in the past.
- `PastOrPresent`: Validates that a temporal value in the past or present.
- `Pattern`: Validates that a `CharSequence` matches a given pattern.
- `Email`: Validates that a `CharSequence` represent a valid email address.
- `Password`: Validates that a password `CharSequence` matches various constraints like length, required or forbidden
  characters or minimum entropy.

Most of these common constraints are named and programmed to work like their siblings from tha Jakarta-Validation-API.
This is done on purpose to simplify migration between these two. The annotations are not pulled in from the
Jakarta-Validation-API, but instead have their own classes inside the Validazor package. Validazor and these
Jakarta-similar constraints can be used entirely without the Jakarta-Validation-API. To support the constraints from
Jakarta, please see the corresponding [chapter](#jakarta-constraint-support).

### Annotate classes:

**Kotlin:**

```Kotlin
data class Person(
    @NotBlank
    @NotNull
    val name: String,

    @PositiveOrZero
    val age: Int,

    @NotBlank
    @Email
    val email: String,
)
```

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

**Note:** Currently, only annotations on classes and fields are support. Getters, setters and methods will not work.
This applies for both, Kotlin and Java.

### Obtain `Validazor` instance

Obtain an instance of `Validazor` to validate an annotated object.

**Kotlin:**

```Kotlin
import de.hsesslingen.keim.validazor.DefaultValidators.*

fun test() {
    val person = Person("Ben", 29, "anonymous@hs-esslingen.de")

    val validator = getDefaultValidator()

    val violations: List<Violation> = validator.validate(person)
    println(violations)

    // Also available:
    val isValid = validazor.isValid(data)

    try {
        validazor.assertValid(data)
    } catch (ex: ViolationException) {
        println(ex.violations)
    }
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

        // Also available:
        boolean isValid = validazor.isValid(data);

        try {
            validazor.assertValid(data);
        } catch (ViolationException ex) {
            System.out.println(ex.getViolations());
        }
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
@Target(AnnotationTarget.FIELD)
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

When defining a custom constraint annotation, make sure to restrict them to fields and/or classes (but also only if you
are fine with doing this). This is done through setting the `@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)`
annotation in Kotlin and the `@Target(ElementType.FIELD)` annotation in Java.

The reason for this is the usage of them in Kotlin. If the annotations are allowed anywhere, using them on fields
without explicitly setting the target to "field", will in many cases result in them being placed somewhere else *but not
on the field*. Read the Kotlin docs about annotation targets for more information.

Either allow them on any field, but then use them as follows on usage sites:

```Kotlin
class Person(
    @field:DividableBy(2) // field: prefix required.
    val age: String
)
```

Or restrict them to only fields and classes and use them freely as follows:

```Kotlin
class Person(
    @DividableBy(2) // No field: prefix needed.
    val age: String
)
```

### Register `ConstraintValidator`

Register your custom constraint together with it's validator as follows:

**Kotlin:**

```kotlin
import de.hsesslingen.keim.validazor.*

fun register() {
    val validazor = Validazor.Builder()
        // Here we register our custom constraint:
        .register(DividableBy.Validator())
        // If we also want the common constraints from Validazor as well,
        // register the default validators using this extension function.
        // However, this is not required for custom constraints to work.
        .registerDefaultValidators()
        .build()

    // use it...
}
```

**Java:**

```java
import de.hsesslingen.keim.validazor.DefaultValidators;

class Whatever {
    void register() {
        var validazor = new Validazor.Builder()
                // Here we register our custom constraint:
                .register(DividableBy.class, new DividableBy.Validator())
                // If we also want the Jakarta constraints and the common ones from Validazor as well,
                // register the `DefaultValidators` as module. However, this is not required for custom
                // constraints to work.
                .register(DefaultValidators::asModule)
                .build();

        // use it...
    }
}
```

This step is supposed to be done on startup of the application, because the retrieved Validazor is stateless and can be
reused. Provide it to your injection framework of choice.

---

## Jakarta-Constraint-Support:

To support the constraints from the Jakarta-Validation-API, please add the following dependencies to your build
configuration:

**Maven:**

```xml

<dependency>
    <groupId>com.github.keim-hs-esslingen</groupId>
    <artifactId>validazor-jakarta</artifactId>
    <version>@{validazorVersion}</version>
</dependency>
<dependency>
   <groupId>jakarta.validation</groupId>
   <artifactId>jakarta.validation-api</artifactId>
   <version>3.0.1</version>
</dependency>
```

**Gradle:**

```kotlin
implementation("com.github.keim-hs-esslingen.validazor:validazor-jakarta:$validazorVersion")
implementation("jakarta.validation:jakarta.validation-api:3.0.1")
```

### Registration of `JakartaModule`:

To enable the validation of Jakarta constraints, register the Jakarta validators on a validazor builder.

**Kotlin:**

```kotlin
fun createValidazor(): Validazor {
    return Validazor.Builder()
        .registerJakartaConstraints() // Extension function.
        .registerCommonConstraints() // If you want to use these as well.
        .build()
}
```

**Java:**

```java
public class Registration {
    void register() {
        var validazor = new Validazor.Builder()
                .register(JakartaConstraints::asModule)
                .register(CommonConstraints::asModule) // If you want to use these as well.
                .build();
    }
}
```

You can now use the Jakarta constraint annotations for validation. If you registered the common constraints as well,
make sure you import the right annotations at the call sites. If you only need the Jakarta constraints and not the
common ones from Validazor, change your build configuration to the following, to avoid pulling in the common constraints
from the `validazor-common-constraints` artifact. They will no longer be on your class path afterwards:

**Maven:**

```xml

<dependencies>
    <dependency>
        <groupId>com.github.keim-hs-esslingen</groupId>
        <artifactId>validazor-core</artifactId>
        <version>@{validazorVersion}</version>
    </dependency>
    <dependency>
        <groupId>com.github.keim-hs-esslingen</groupId>
        <artifactId>validazor-jakarta</artifactId>
        <version>@{validazorVersion}</version>
    </dependency>
    <dependency>
        <groupId>jakarta.validation</groupId>
        <artifactId>jakarta.validation-api</artifactId>
        <version>3.0.1</version>
    </dependency>
</dependencies>
```

**Gradle:**

```kotlin
implementation("com.github.keim-hs-esslingen.validazor:validazor-core:$validazorVersion")
implementation("com.github.keim-hs-esslingen.validazor:validazor-jakarta:$validazorVersion")
implementation("jakarta.validation:jakarta.validation-api:3.0.1")
```

### Important for Kotlin users of Jakarta constraints with Validazor:

**Important:** If you use the Jakarta constraints annotations on Kotlin code, there is a drawback that you must accept
for this to work. You must explicitly set the annotation target of annotated fields to "field" by defining the
annotation constraints as follows:

```kotlin
class Person(
    @field:NotBlank // use field: prefix on annotations from the Jakarta module.
    val name: String
)
```

This is required because the Jakarta constraints are implemented to work at any place. This makes Kotlin use a default
order of placement sites when compiling to byte code in which the fields are not the first priority. Resulting byte code
classes do not contain the annotations on the fields and the validation constraints will be ignored. For more
information read the Kotlin docs on the topic of annotation targets.

Using the common constraints provided by default from the Validazor package do *not* have this restriction, as they are
allowed only on fields and classes.



---

## General Drawbacks:

Validazor has some general drawbacks compared to the Jakarta-Validation-API.

These are:

- No support for validation groups.
- Kotlin-Stdlib is required as transitive dependency for Java users (might change in future)
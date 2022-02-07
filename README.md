# Validazor

A JVM-Library written in Kotlin for validating objects using annotations. Similar to Jakarta-Validation-API but simpler
to use.

---

## Dependency

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
    implementation("com.github.keim-hs-esslingen:validazor:$validazorVersion")
```

You can find the latest version on the release page.

---

## Usage:

Annotate classes that should be validated with any of the Jakarta-API constraint annotations or any of the common
constraints provided by Validazor.

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

If using Kotlin, the annotations must be specialized to be put on the fields explicitly. This restriction might be
removed in the future.

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

If using Java, annotations on getters and setters are not yet supported. They might be supported in the future.

Obtain an instance of `Validazor` to validate an annotated object.

**Kotlin:**

```Kotlin
import de.hsesslingen.keim.validazor.DefaultValidatorsModule.*

fun test() {
    val person = Person("Ben", 29, "anonymous@hs-esslingen.de")
    val validator = getDefaultValidator()
    val violations = validator.validate(person) // returns Set<Violation>
    println(violations)
}
```

**Java:**

```java
import static de.hsesslingen.keim.validazor.DefaultValidatorsModule.getDefaultValidator;

class TestValidazor {
    void test() {
        Person person = new Person("Ben", 29, "anonymous@hs-esslingen.de");
        Validazor validator = getDefaultValidator();
        Set<Violation> violations = validator.validate(person);
        System.out.println(violations);
    }
}
```

If the returned set of violations is empty, the tested object can be considered valid. If the set however is not empty
the tested object *must* be considered invalid. In this case the violation items provide further information about what
is wrong with the object.

---

## Custom Constraints

Implementing own constraints is very easy using Validazor.

1. Create an annotation class with all the properties needed.
2. Create a class implementing the `ConstraintValidazor<A>` interface with `A` being the type of your custom
   constraint annotation.
3. Register an instance of your validator at a `Validazor.Builder` instance and build a validator from it.

Need an example? Here you go:

**Kotlin:**
```kotlin
annotation class Even {
    class Validator : ConstraintValidazor<Even> {
        override fun validate(
            constraint: Even,
            value: Any?,
            path: PropertyPath,
            violations: ViolationCollector,
            returnOnFirstViolation: Boolean,
        ) {
            when (value) {
                null -> {}
                is Int, Long, Short, UInt, ULong, UShort -> if (value % 2 != 0) {
                    violations.add("must be even", path, constraint.toConstraintInfo())
                }
                else -> {}
            }
        }
    }
}

fun test() {
    val validator = Valizator.Builder()
                        .registerDefaultValidators() // Can be removed if not needed.
                        .register(Even.Validator())
                        .build()
   
    // validate objects, or hand over to injection framework...                          
}
```


**Java:**
 TBD
package de.hsesslingen.keim.validazor.test.java.dividableby;

import de.hsesslingen.keim.validazor.DefaultValidators;
import de.hsesslingen.keim.validazor.Validazor;
import de.hsesslingen.keim.validazor.ViolationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DividableByTest {

    @Test
    void test() {
        // Create test object.
        var data = new Data();
        data.intValue = 3;
        data.longValue = 4L;

        // Register the custom validator and build a Validazor instance.
        // This step is supposed to be done on startup of the application
        // because the retrieved Validazor is stateless and can be reused.
        // Provide it to your injection framework of choice.
        var validazor = new Validazor.Builder()
                // Here we register our custom constraint:
                .register(DividableBy.class, new DividableBy.Validator())
                // If we also want the Jakarta constraints and the common ones from Validazor as well,
                // register the `DefaultValidators` as module. This is not required for custom
                // constraints to work.
                .register(DefaultValidators::asModule)
                .build();

        // Validate and asses violations
        var violations = validazor.validate(data);
        System.out.println(violations);

        // Also available:
        boolean isValid = validazor.isValid(data);

        try {
            validazor.assertValid(data);
        } catch (ViolationException ex) {
            System.out.println(ex.getViolations());
        }

        assertTrue(violations.stream()
                .anyMatch(it -> it.getPath().equals("intValue") && it.getMessage().equals("must be dividable by 2")));
        assertTrue(violations.stream()
                .anyMatch(it -> it.getPath().equals("longValue") && it.getMessage().equals("must be dividable by 3")));
    }

}

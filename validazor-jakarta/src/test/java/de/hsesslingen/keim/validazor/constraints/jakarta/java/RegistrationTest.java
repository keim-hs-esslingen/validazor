package de.hsesslingen.keim.validazor.constraints.jakarta.java;

import de.hsesslingen.keim.validazor.Validazor;
import de.hsesslingen.keim.validazor.constraints.jakarta.JakartaConstraints;
import org.junit.jupiter.api.Test;

public class RegistrationTest {
    @Test
    void register() {
        var validazor = new Validazor.Builder()
                .register(JakartaConstraints::asModule)
                .build();
    }
}

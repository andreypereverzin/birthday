package org.llamaland.birthday.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameValidatorTest {
    private NameValidator nameValidator;

    @BeforeEach
    void setUp() {
        nameValidator = new NameValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Brown", "O'Rourke", "Von Tappo", "von Tappo", "Alfredo",
            "Jean-Mary", "Simpson Jr."})
    void isValid_shouldAcceptValidName(String name) {
        assertTrue(nameValidator.isValid(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Brown@", "Bobby1", "Betsy/"})
    void isValid_shouldRejectInvalidName(String name) {
        assertFalse(nameValidator.isValid(name));
    }

    @Test
    void isValid_shouldRejectNullName() {
        assertFalse(nameValidator.isValid(null));
    }
}

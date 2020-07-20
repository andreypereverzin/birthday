package org.llamaland.birthday.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {
    private EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "me@gmail.com",
            "me@me.co.uk",
            "me-100@me.co.uk",
            "me+100@me.co.uk",
            "me.100@me.co.uk",
            "me@1.com",
            "me@yahoo-test.com",
            "bobby.brown@ilovellamaland.com",
            "betsy@heyitsme.com",
            "alfie@vontappo.llama.land"
    })
    void isEmailValid_shouldAcceptValidEmail(String email) {
        assertTrue(emailValidator.isValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "me@.gmail.com",
            "me@.com",
            "me@%*.com",
            "me@me@me.co.uk",
            "me@me.co.uk.1a"
    })
    void isValid_shouldRejectInvalidEmail(String email) {
        assertFalse(emailValidator.isValid(email));
    }

    @Test
    void isValid_shouldRejectNullEmail() {
        assertFalse(emailValidator.isValid(null));
    }
}

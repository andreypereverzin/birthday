package org.llamaland.birthday.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptedOutEmailsReaderTest {
    private OptedOutEmailsReader optedOutEmailsReader;

    @BeforeEach
    void setUp() {
        optedOutEmailsReader = new OptedOutEmailsReader();
    }

    @Test
    void getOptedOutEmails() {
    }
    @Test
    void getOptedOutEmails_shouldHandleEmptyStream() throws Exception {
        // given
        String lines = "";

        // when
        Set<String> emails = optedOutEmailsReader.getOptedOutEmails(new StringReader(lines));

        // then
        assertEquals(emails.size(), 0);
    }

    @Test
    void getOptedOutEmails_shouldHandleSeveralLines() throws Exception {
        // given
        String lines = "email1@gmail.com\n" +
                "email2@gmail.com";

        // when
        Set<String> emails = optedOutEmailsReader.getOptedOutEmails(new StringReader(lines));

        // then
        assertEquals(emails.size(), 2);
        assertTrue(emails.contains("email1@gmail.com"));
        assertTrue(emails.contains("email2@gmail.com"));
    }

    @Test
    void getOptedOutEmails_shouldSkipUnparseableLine() throws Exception {
        // given
        String lines = "email1@gmail.com\n" +
                "email2\n" +
                "email3@gmail.com";

        // when
        Set<String> emails = optedOutEmailsReader.getOptedOutEmails(new StringReader(lines));

        // then
        assertEquals(emails.size(), 2);
        assertTrue(emails.contains("email1@gmail.com"));
        assertTrue(emails.contains("email3@gmail.com"));
    }
}
package org.llamaland.birthday.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llamaland.birthday.data.Citizen;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailsRemoverTest {
    private EmailsRemover emailsRemover;

    @BeforeEach
    void setUp() {
        emailsRemover = new EmailsRemover();
    }

    @Test
    void removeCitizensWithDuplicateAndOptedOutEmails_shouldHandleEmptyList() {
        assertEquals(emailsRemover.removeCitizensWithDuplicateAndOptedOutEmails(emptyList(), emptySet()).size(), 0);
        assertEquals(emailsRemover.removeCitizensWithDuplicateAndOptedOutEmails(emptyList(), Collections.singleton("email1@gmail.com")).size(), 0);
    }

    @Test
    void removeCitizensWithDuplicateAndOptedOutEmails_shouldRemoveDuplicateEmailsWithEmptyOptedOutEmails() {
        // given
        List<Citizen> citizens = asList(
                new Citizen("namea", "nameb", LocalDate.of(1900, 1, 2), "email1@gmail.com"),
                new Citizen("namec", "named", LocalDate.of(1900, 1, 2), "email2@gmail.com"),
                new Citizen("namee", "namef", LocalDate.of(1900, 1, 2), "email1@gmail.com"),
                new Citizen("nameg", "nameh", LocalDate.of(1900, 1, 2), "email3@gmail.com"),
                new Citizen("namei", "namej", LocalDate.of(1900, 1, 2), "email2@gmail.com"),
                new Citizen("namek", "namel", LocalDate.of(1900, 1, 2), "email4@gmail.com")
        );

        // when
        List<Citizen> citizensWithoutDuplicateEmails =
                emailsRemover.removeCitizensWithDuplicateAndOptedOutEmails(citizens, emptySet());

        // then
        assertEquals(citizensWithoutDuplicateEmails.size(), 2);
        assertEquals(citizensWithoutDuplicateEmails.get(0).getLastName(), "nameg");
        assertEquals(citizensWithoutDuplicateEmails.get(0).getFirstName(), "nameh");
        assertEquals(citizensWithoutDuplicateEmails.get(0).getEmail(), "email3@gmail.com");
        assertEquals(citizensWithoutDuplicateEmails.get(1).getLastName(), "namek");
        assertEquals(citizensWithoutDuplicateEmails.get(1).getFirstName(), "namel");
        assertEquals(citizensWithoutDuplicateEmails.get(1).getEmail(), "email4@gmail.com");
    }

    @Test
    void removeCitizensWithDuplicateAndOptedOutEmails_shouldRemoveDuplicateEmailsWithOptedOutEmails() {
        // given
        List<Citizen> citizens = asList(
                new Citizen("namea", "nameb", LocalDate.of(1900, 1, 2), "email1@gmail.com"),
                new Citizen("namec", "named", LocalDate.of(1900, 1, 2), "email2@gmail.com"),
                new Citizen("namee", "namef", LocalDate.of(1900, 1, 2), "email1@gmail.com"),
                new Citizen("nameg", "nameh", LocalDate.of(1900, 1, 2), "email3@gmail.com"),
                new Citizen("namei", "namej", LocalDate.of(1900, 1, 2), "email2@gmail.com"),
                new Citizen("namek", "namel", LocalDate.of(1900, 1, 2), "email4@gmail.com")
        );

        // when
        List<Citizen> citizensWithoutDuplicateEmails =
                emailsRemover.removeCitizensWithDuplicateAndOptedOutEmails(citizens, Collections.singleton("email4@gmail.com"));

        // then
        assertEquals(citizensWithoutDuplicateEmails.size(), 1);
        assertEquals(citizensWithoutDuplicateEmails.get(0).getLastName(), "nameg");
        assertEquals(citizensWithoutDuplicateEmails.get(0).getFirstName(), "nameh");
        assertEquals(citizensWithoutDuplicateEmails.get(0).getEmail(), "email3@gmail.com");
    }
}

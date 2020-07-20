package org.llamaland.birthday.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llamaland.birthday.data.Citizen;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CitizensProviderTest {
    private CitizensProvider citizensProvider;

    @BeforeEach
    void setUp() {
        citizensProvider = new CitizensProvider();
    }

    @Test
    void getCitizensToSendEmailTo_shouldHandleTwoCitizens() {
        // given
        List<Citizen> citizens = asList(
                new Citizen("namea", "nameb", LocalDate.of(1970, 7, 22), "email1@gmail.com"),
                new Citizen("namec", "named", LocalDate.of(1970, 7, 22), "email2@gmail.com")
        );

        // when
        List<Citizen> citizensToSendEmailTo =
                citizensProvider.getCitizensToSendEmailTo(
                        citizens,
                        LocalDate.of(2020, 7, 20),
                        50,
                        2,
                        3,
                        2
                );

        // then
        assertEquals(citizensToSendEmailTo.size(), 2);
        assertEquals(citizensToSendEmailTo.get(0).getLastName(), "namea");
        assertEquals(citizensToSendEmailTo.get(0).getFirstName(), "nameb");
        assertEquals(citizensToSendEmailTo.get(0).getEmail(), "email1@gmail.com");
        assertEquals(citizensToSendEmailTo.get(1).getLastName(), "namec");
        assertEquals(citizensToSendEmailTo.get(1).getFirstName(), "named");
        assertEquals(citizensToSendEmailTo.get(1).getEmail(), "email2@gmail.com");
    }

    @Test
    void getCitizensToSendEmailTo_shouldHandleThresholdWhenCitizensShouldBeSelectedEarlier() {
        // given
        List<Citizen> citizens = asList(
                new Citizen("namea", "nameb", LocalDate.of(1970, 7, 22), "email1@gmail.com"),
                new Citizen("namec", "named", LocalDate.of(1970, 7, 22), "email2@gmail.com"),
                new Citizen("namee", "namef", LocalDate.of(1970, 7, 22), "email3@gmail.com")
        );

        // when
        List<Citizen> citizensToSendEmailTo =
                citizensProvider.getCitizensToSendEmailTo(
                        citizens,
                        LocalDate.of(2020, 7, 20),
                        50,
                        2,
                        3,
                        2
                );

        // then
        assertEquals(citizensToSendEmailTo.size(), 0);
    }

    @Test
    void getCitizensToSendEmailTo_shouldHandleALotThresholdIfItHasNotBeenHit() {
        // given
        List<Citizen> citizens = asList(
                new Citizen("namea", "nameb", LocalDate.of(1970, 7, 22), "email1@gmail.com"),
                new Citizen("namee", "namef", LocalDate.of(1970, 7, 22), "email2@gmail.com")
        );

        // when
        List<Citizen> citizensToSendEmailTo =
                citizensProvider.getCitizensToSendEmailTo(
                        citizens,
                        LocalDate.of(2020, 7, 20),
                        50,
                        1,
                        2,
                        2
                );

        // then
        assertEquals(citizensToSendEmailTo.size(), 0);
    }

    @Test
    void getCitizensToSendEmailTo_shouldHandleALotThresholdIfItHasBeenHit() {
        // given
        List<Citizen> citizens = asList(
                new Citizen("namea", "nameb", LocalDate.of(1970, 7, 22), "email1@gmail.com"),
                new Citizen("namec", "named", LocalDate.of(1970, 7, 22), "email2@gmail.com"),
                new Citizen("namee", "namef", LocalDate.of(1970, 7, 22), "email3@gmail.com")
        );

        // when
        List<Citizen> citizensToSendEmailTo =
                citizensProvider.getCitizensToSendEmailTo(
                        citizens,
                        LocalDate.of(2020, 7, 20),
                        50,
                        1,
                        2,
                        2
                );

        // then
        assertEquals(citizensToSendEmailTo.size(), 3);
        assertEquals(citizensToSendEmailTo.get(0).getLastName(), "namea");
        assertEquals(citizensToSendEmailTo.get(0).getFirstName(), "nameb");
        assertEquals(citizensToSendEmailTo.get(0).getEmail(), "email1@gmail.com");
        assertEquals(citizensToSendEmailTo.get(1).getLastName(), "namec");
        assertEquals(citizensToSendEmailTo.get(1).getFirstName(), "named");
        assertEquals(citizensToSendEmailTo.get(1).getEmail(), "email2@gmail.com");
        assertEquals(citizensToSendEmailTo.get(2).getLastName(), "namee");
        assertEquals(citizensToSendEmailTo.get(2).getFirstName(), "namef");
        assertEquals(citizensToSendEmailTo.get(2).getEmail(), "email3@gmail.com");
    }

    @Test
    void getCitizensToSendEmailTo_shouldHandleSeveralBirthdayDates() {
        // given
        List<Citizen> citizens = asList(
                new Citizen("namea", "nameb", LocalDate.of(1970, 7, 21), "email1@gmail.com"),
                new Citizen("namec", "named", LocalDate.of(1970, 7, 22), "email2@gmail.com"),
                new Citizen("namee", "namef", LocalDate.of(1970, 7, 23), "email3@gmail.com"),
                new Citizen("nameg", "nameh", LocalDate.of(1970, 7, 23), "email4@gmail.com"),
                new Citizen("namei", "namej", LocalDate.of(1970, 7, 23), "email5@gmail.com"),
                new Citizen("namek", "namel", LocalDate.of(1970, 7, 24), "email6@gmail.com")
        );

        // when
        List<Citizen> citizensToSendEmailTo =
                citizensProvider.getCitizensToSendEmailTo(
                        citizens,
                        LocalDate.of(2020, 7, 20),
                        50,
                        2,
                        3,
                        2
                );

        // then
        assertEquals(citizensToSendEmailTo.size(), 4);
        assertEquals(citizensToSendEmailTo.get(0).getLastName(), "namec");
        assertEquals(citizensToSendEmailTo.get(0).getFirstName(), "named");
        assertEquals(citizensToSendEmailTo.get(0).getEmail(), "email2@gmail.com");
        assertEquals(citizensToSendEmailTo.get(1).getLastName(), "namee");
        assertEquals(citizensToSendEmailTo.get(1).getFirstName(), "namef");
        assertEquals(citizensToSendEmailTo.get(1).getEmail(), "email3@gmail.com");
        assertEquals(citizensToSendEmailTo.get(2).getLastName(), "nameg");
        assertEquals(citizensToSendEmailTo.get(2).getFirstName(), "nameh");
        assertEquals(citizensToSendEmailTo.get(2).getEmail(), "email4@gmail.com");
        assertEquals(citizensToSendEmailTo.get(3).getLastName(), "namei");
        assertEquals(citizensToSendEmailTo.get(3).getFirstName(), "namej");
        assertEquals(citizensToSendEmailTo.get(3).getEmail(), "email5@gmail.com");
    }
}

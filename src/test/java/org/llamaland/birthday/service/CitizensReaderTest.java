package org.llamaland.birthday.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llamaland.birthday.data.Citizen;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CitizensReaderTest {
    private CitizensReader citizensReader;

    @BeforeEach
    void setUp() {
        citizensReader = new  CitizensReader();
    }

    @Test
    void getCitizens_shouldHandleEmptyStream() throws Exception {
        // given
        String lines = "";

        // when
        List<Citizen> citizens = citizensReader.getCitizens(new StringReader(lines));

        // then
        assertEquals(citizens.size(), 0);
    }

    @Test
    void getCitizens_shouldHandleSeveralLines() throws Exception {
        // given
        String lines = "namea,nameb,01-11-1920,email1@gmail.com\n" +
                "namec,named,01-11-1920,email2@gmail.com";

        // when
        List<Citizen> citizens = citizensReader.getCitizens(new StringReader(lines));

        // then
        assertEquals(citizens.size(), 2);
        assertEquals(citizens.get(0).getLastName(), "namea");
        assertEquals(citizens.get(0).getFirstName(), "nameb");
        assertEquals(citizens.get(0).getEmail(), "email1@gmail.com");
        assertEquals(citizens.get(1).getLastName(), "namec");
        assertEquals(citizens.get(1).getFirstName(), "named");
        assertEquals(citizens.get(1).getEmail(), "email2@gmail.com");
    }

    @Test
    void getCitizens_shouldSkipUnparseableLine() throws Exception {
        // given
        String lines = "namea,nameb,01-11-1920,email1@gmail.com\n" +
                "namec,named,01-1-1920,email2@gmail.com\n" +
                "namee,namef,01-11-1920,email3@gmail.com";

        // when
        List<Citizen> citizens = citizensReader.getCitizens(new StringReader(lines));

        // then
        assertEquals(citizens.size(), 2);
        assertEquals(citizens.get(0).getLastName(), "namea");
        assertEquals(citizens.get(0).getFirstName(), "nameb");
        assertEquals(citizens.get(0).getEmail(), "email1@gmail.com");
        assertEquals(citizens.get(1).getLastName(), "namee");
        assertEquals(citizens.get(1).getFirstName(), "namef");
        assertEquals(citizens.get(1).getEmail(), "email3@gmail.com");
    }
}

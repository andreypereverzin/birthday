package org.llamaland.birthday.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llamaland.birthday.data.Citizen;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CitizensPrinterTest {
    private CitizensPrinter citizensPrinter;

    @BeforeEach
    void setUp() {
        citizensPrinter = new CitizensPrinter();
    }

    @Test
    void printCitizens_shouldHandleEmptyList() {
        // given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // when
        citizensPrinter.printCitizens(emptyList(), new PrintStream(baos));

        // then
        assertEquals(baos.toString(), "");
    }

    @Test
    void printCitizens_shouldHandleListOfCitizens() {
        // given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        List<Citizen> citizens = asList(
                new Citizen("namea", "nameb", LocalDate.of(1970, 7, 22), "email1@gmail.com"),
                new Citizen("namec", "named", LocalDate.of(1970, 7, 22), "email2@gmail.com")
        );

        // when
        citizensPrinter.printCitizens(citizens, new PrintStream(baos));

        // then
        assertEquals(baos.toString(),
                "namea,nameb,22-07-1970,email1@gmail.com\n" +
                "namec,named,22-07-1970,email2@gmail.com\n");
    }
}

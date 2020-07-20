package org.llamaland.birthday.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CitizensEmailServiceTest {
    private CitizensEmailService citizensEmailService;

    @BeforeEach
    void setUp() {
        citizensEmailService = new CitizensEmailService();
    }

    @Test
    void processCitizens_shouldProcessTestFiles() throws Exception {
        // given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // when
        citizensEmailService.processCitizens(
                LocalDate.of(2020, 7, 20),
                100,
                5,
                10,
                20,
                "src/test/resources/citizens.csv",
                "src/test/resources/emails.csv",
                new PrintStream(baos)
        );

        // then
        assertEquals(baos.toString(), "Brown,Bobby,27-07-1920,bobby.brown@ilovellamaland.com\n");
    }
}
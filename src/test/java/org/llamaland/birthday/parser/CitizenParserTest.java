package org.llamaland.birthday.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.llamaland.birthday.data.Citizen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CitizenParserTest {
    private CitizenParser citizenParser;

    @BeforeEach
    void setUp() {
        citizenParser = new CitizenParser();
    }

    @ParameterizedTest
    @CsvSource({"Brown,Bobby,10-11-1950,bobby.brown@ilovellamaland.com,Brown,Bobby,bobby.brown@ilovellamaland.com",
            "O'Rourke,Betsy,28-02-1900,betsy@heyitsme.com,O'Rourke,Betsy,betsy@heyitsme.com",
            "Von Tappo,Alfredo,01-01-1920,alfie@vontappo.llama.land,Von Tappo,Alfredo,alfie@vontappo.llama.land"})
    void parseLine_shouldParseValidLine(String lastName, String firstName, String date,
                                        String email, String expectedLastName,
                                        String expectedFirstName, String expectedEmail) throws Exception {
        // given
        String line = getLine(lastName, firstName, date, email);

        // when
        Citizen citizen = citizenParser.parseLine(line);

        // then
        assertEquals(citizen.getLastName(), expectedLastName);
        assertEquals(citizen.getFirstName(), expectedFirstName);
        assertEquals(citizen.getEmail(), expectedEmail);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "Brown,Bobby,10-11-1950",
            "O'Rourke,Betsy,28-02-1900,betsy@heyitsme.com,"
    })
    void parseLine_shouldRejectInvalidNumberOfValues(String line) {
        Exception exception = assertThrows(
                ParsingException.class,
                () -> citizenParser.parseLine(line));

        assertEquals(exception.getMessage(), "Error parsing line " + line);
    }

    @ParameterizedTest
    @CsvSource({
            "Brown/,Bobby,10-11-1950,bobby.brown@ilovellamaland.com,Error parsing last name Brown/",
            "Brown,Bobby/,10-11-1950,bobby.brown@ilovellamaland.com,Error parsing first name Bobby/",
            "Brown,Bobby,10-11-1950,bobby.brown@.ilovellamaland.com,Error parsing email bobby.brown@.ilovellamaland.com"
    })
    void parseLine_shouldRejectInvalidValues(String lastName, String firstName, String date,
                                        String email, String expectedMessage) {
        String line = getLine(lastName, firstName, date, email);

        Exception exception = assertThrows(
                ParsingException.class,
                () -> citizenParser.parseLine(line));

        assertEquals(exception.getMessage(), expectedMessage + " in line " + line);
    }

    private String getLine(String lastName, String firstName, String date, String email) {
        return lastName + "," + firstName + "," + date + "," + email;
    }
}
package org.llamaland.birthday.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateParserTest {
    private DateParser dateParser;

    @BeforeEach
    void setUp() {
        dateParser = new DateParser();
    }

    @ParameterizedTest
    @CsvSource({
            "10-11-1950,10,11,1950",
            "28-02-1900,28,2,1900",
            "01-01-1920,1,1,1920"
    })
    void parseDate_shouldParseValidDate(String date, String day, String month, String year) throws Exception {
        LocalDate localDate = dateParser.parseDate(date);
        assertEquals(localDate.getDayOfMonth(), Integer.parseInt(day));
        assertEquals(localDate.getMonthValue(), Integer.parseInt(month));
        assertEquals(localDate.getYear(), Integer.parseInt(year));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "10/11/1950",
            "02-28-1900",
            "1920-01-01",
            "1-01-1920",
            "01-1-1920",
            "01-01-20"
    })
    void parseDate_shouldRejectInvalidDate(String date) {
        Exception exception = assertThrows(
                ParsingException.class,
                () -> dateParser.parseDate(date));

        assertEquals(exception.getMessage(), "Error parsing date " + date);
    }

    @Test
    void parseDate_shouldRejectNullDate() {
        Exception exception = assertThrows(
                ParsingException.class,
                () -> dateParser.parseDate(null));

        assertEquals(exception.getMessage(), "Error parsing date null");
    }
}

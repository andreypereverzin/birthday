package org.llamaland.birthday.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    public LocalDate parseDate(String date) throws ParsingException {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch(Exception ex) {
            throw new ParsingException("Error parsing date " + date, ex);
        }
    }
}

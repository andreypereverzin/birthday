package org.llamaland.birthday.parser;

import org.llamaland.birthday.data.Citizen;

import java.time.LocalDate;

public class CitizenParser {
    private final DateParser dateParser = new DateParser();
    private final EmailValidator emailValidator = new EmailValidator();
    private final NameValidator nameValidator = new NameValidator();

    public Citizen parseLine(String line) throws ParsingException {
        String[] values = line.split(",", -1);
        if (values.length != 4) {
            throw new ParsingException("Error parsing line " + line);
        }

        LocalDate birthDate = dateParser.parseDate(values[2]);

        if (!nameValidator.isValid(values[0])) {
            throw new ParsingException("Error parsing last name " + values[0] + " in line " + line);
        }

        if (!nameValidator.isValid(values[1])) {
            throw new ParsingException("Error parsing first name " + values[1] + " in line " + line);
        }

        if (!emailValidator.isValid(values[3])) {
            throw new ParsingException("Error parsing email " + values[3] + " in line " + line);
        }

        return new Citizen(values[0].trim(), values[1].trim(), birthDate, values[3].trim());
    }
}

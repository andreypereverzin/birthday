package org.llamaland.birthday.service;

import org.llamaland.birthday.data.Citizen;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CitizensPrinter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    public void printCitizens(List<Citizen>citizens, PrintStream ps) {
        citizens.forEach(
                citizen -> ps.println(
                        citizen.getLastName() + ","
                        + citizen.getFirstName() + ","
                        + citizen.getBirthDate().format(DATE_FORMATTER) + ","
                        + citizen.getEmail()
                )
        );
    }
}

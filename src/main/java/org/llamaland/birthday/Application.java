package org.llamaland.birthday;

import org.llamaland.birthday.service.CitizensEmailService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class Application {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java -jar birthday.jar <path-to-citizens-file> <path-to-opted-out-emails-file>");
            System.exit(1);
        }

        Properties appProps = new PropertiesReader().readProperties("app.properties");
        int age = Integer.parseInt(appProps.getProperty("age"));
        int weekdaysInAdvance = Integer.parseInt(appProps.getProperty("weekdaysInAdvance"));
        int weekdaysALotInAdvance = Integer.parseInt(appProps.getProperty("weekdaysALotInAdvance"));
        int aLotThreashold = Integer.parseInt(appProps.getProperty("aLotThreashold"));

        new CitizensEmailService().processCitizens(
                LocalDate.now(),
                age,
                weekdaysInAdvance,
                weekdaysALotInAdvance,
                aLotThreashold,
                args[0],
                args[1],
                System.out
        );
    }
}

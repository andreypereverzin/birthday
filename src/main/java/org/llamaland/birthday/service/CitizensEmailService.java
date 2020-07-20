package org.llamaland.birthday.service;

import org.llamaland.birthday.data.Citizen;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CitizensEmailService {
    private final CitizensReader citizensReader = new CitizensReader();
    private final OptedOutEmailsReader optedOutEmailsReader = new OptedOutEmailsReader();
    private final EmailsRemover emailsRemover = new EmailsRemover();
    private final CitizensProvider citizensProvider = new CitizensProvider();
    private final CitizensPrinter citizensPrinter = new CitizensPrinter();

    public void processCitizens(
            LocalDate date,
            int age,
            int weekdaysInAdvance,
            int weekdaysALotInAdvance,
            int aLotThreshold,
            String citizensFileName,
            String optedOutEmailsFileName,
            PrintStream ps
    ) throws IOException {
        List<Citizen> citizens =
                citizensReader.getCitizens(new FileReader(new File(citizensFileName)));
        Set<String> optedOutEmails =
                optedOutEmailsReader.getOptedOutEmails(new FileReader(new File(optedOutEmailsFileName)));
        List<Citizen> citizensWithoutDuplicateAndOptedOutEmails =
                emailsRemover.removeCitizensWithDuplicateAndOptedOutEmails(citizens, optedOutEmails);
        List<Citizen> citizensToSendEmailTo = citizensProvider.getCitizensToSendEmailTo(
                citizensWithoutDuplicateAndOptedOutEmails,
                date,
                age,
                weekdaysInAdvance,
                weekdaysALotInAdvance,
                aLotThreshold
        );

        citizensPrinter.printCitizens(citizensToSendEmailTo, ps);
    }
}

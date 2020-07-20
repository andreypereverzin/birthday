package org.llamaland.birthday.service;

import org.llamaland.birthday.data.Citizen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CitizensProvider {
    private final DateProvider dateProvider = new DateProvider();

    public List<Citizen> getCitizensToSendEmailTo(
            List<Citizen> citizens,
            LocalDate todayDate,
            int age,
            int weekdaysInAdvance,
            int weekdaysALotInAdvance,
            int aLotThreshold
    ) {
        List<LocalDate> advanceDates = dateProvider.getBirthdayDates(todayDate, weekdaysInAdvance);
        List<LocalDate> advanceALotDates = dateProvider.getBirthdayDates(todayDate, weekdaysALotInAdvance);

        List<Citizen> citizensToSendEmailTo = new ArrayList<>();

        for (LocalDate date: advanceDates) {
            LocalDate birthDate = date.minusYears(age);
            List<Citizen> citizensWithBirthDate = citizens.stream()
                    .filter(citizen -> citizen.getBirthDate().equals(birthDate))
                    .collect(toList());
            if (citizensWithBirthDate.size() <= aLotThreshold) {
                // if number of citizens with this birth date is more than the threshold
                // they were selected earlier
                citizensToSendEmailTo.addAll(citizensWithBirthDate);
            }
        }

        for (LocalDate date: advanceALotDates) {
            LocalDate birthDate = date.minusYears(age);
            List<Citizen> citizensWithBirthDate = citizens.stream()
                    .filter(citizen -> citizen.getBirthDate().equals(birthDate))
                    .collect(toList());
            if (citizensWithBirthDate.size() > aLotThreshold) {
                // if number of citizens with this birth date is less or equal than the threshold
                // they should be selected later
                citizensToSendEmailTo.addAll(citizensWithBirthDate);
            }
        }

        return citizensToSendEmailTo;
    }
}

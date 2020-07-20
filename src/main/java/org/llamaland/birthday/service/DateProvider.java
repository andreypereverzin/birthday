package org.llamaland.birthday.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

/**
 * Logic implemented in this class is described in README.md file, chapter Assumptions.
 */
public class DateProvider {
    private static final List<DayOfWeek> WEEKEND_DAYS = asList(SATURDAY, SUNDAY);

    public List<LocalDate> getBirthdayDates(LocalDate date, int weekdays) {
        if (weekdays < 0) {
            throw new IllegalArgumentException("Number of weekdays cannot be negative");
        }

        if (WEEKEND_DAYS.contains(date.getDayOfWeek())) {
            return emptyList();
        }

        List<LocalDate> birthdayDates = new ArrayList<>();

        int count = weekdays;
        LocalDate dt = date;
        while(count > 0) {
            dt = dt.plusDays(1);
            if (!WEEKEND_DAYS.contains(dt.getDayOfWeek())) {
                // skip weekdays
                count--;
            } else if (count == 1) {
                // include weekend days which are just before the weekday we are after
                birthdayDates.add(dt);
            }
        }
        birthdayDates.add(dt);

        return birthdayDates;
    }
}

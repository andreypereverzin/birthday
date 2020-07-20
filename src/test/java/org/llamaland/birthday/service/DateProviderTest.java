package org.llamaland.birthday.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateProviderTest {
    private DateProvider dateProvider;

    @BeforeEach
    void setUp() {
        dateProvider = new DateProvider();
    }

    @Test
    void getBirthdayDates_shouldThrowIfNegativeWorkdays() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> dateProvider.getBirthdayDates(LocalDate.now(), -1));

        assertEquals(exception.getMessage(), "Number of weekdays cannot be negative");
    }

    @ParameterizedTest
    @CsvSource({"18,7,2020", "19,7,2020"})
    void parseDate_shouldReturnEmptyListForSaturdayAndSunday(String day, String month, String year) {
        LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        assertEquals(dateProvider.getBirthdayDates(localDate, 1).size(), 0);
    }

    @ParameterizedTest
    @CsvSource({
            "20,7,2020,21,7,2020",
            "21,7,2020,22,7,2020",
            "22,7,2020,23,7,2020",
            "23,7,2020,24,7,2020"
    })
    void parseDate_shouldReturnSingleDateForMondayToThursdayOneWeekdayInAdvance(
            String day, String month, String year,
            String expectedDay, String expectedMonth, String expectedYar
    ) {
        // given
        LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        // when
        List<LocalDate> birthdayDates = dateProvider.getBirthdayDates(localDate, 1);

        // then
        assertEquals(birthdayDates.size(), 1);
        assertEquals(birthdayDates.get(0).getDayOfMonth(), Integer.parseInt(expectedDay));
        assertEquals(birthdayDates.get(0).getMonthValue(), Integer.parseInt(expectedMonth));
        assertEquals(birthdayDates.get(0).getYear(), Integer.parseInt(expectedYar));
    }

    @Test
    void parseDate_shouldReturnThreeDatesForFridayOneWeekdayInAdvance() {
        // given
        LocalDate localDate = LocalDate.of(2020, 7, 24);

        // when
        List<LocalDate> birthdayDates = dateProvider.getBirthdayDates(localDate, 1);

        // then
        assertEquals(birthdayDates.size(), 3);
        assertEquals(birthdayDates.get(0).getDayOfMonth(), 25);
        assertEquals(birthdayDates.get(0).getMonthValue(), 7);
        assertEquals(birthdayDates.get(0).getYear(), 2020);
        assertEquals(birthdayDates.get(1).getDayOfMonth(), 26);
        assertEquals(birthdayDates.get(1).getMonthValue(), 7);
        assertEquals(birthdayDates.get(1).getYear(), 2020);
        assertEquals(birthdayDates.get(2).getDayOfMonth(), 27);
        assertEquals(birthdayDates.get(2).getMonthValue(), 7);
        assertEquals(birthdayDates.get(2).getYear(), 2020);
    }

    @ParameterizedTest
    @CsvSource({
            "21,7,2020,28,7,2020",
            "22,7,2020,29,7,2020",
            "23,7,2020,30,7,2020",
            "24,7,2020,31,7,2020"
    })
    void parseDate_shouldReturnSingleDateForTuesdayToFridayFiveWeekdaysInAdvance(
            String day, String month, String year,
            String expectedDay, String expectedMonth, String expectedYar
    ) {
        // given
        LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        // when
        List<LocalDate> birthdayDates = dateProvider.getBirthdayDates(localDate, 5);

        // then
        assertEquals(birthdayDates.size(), 1);
        assertEquals(birthdayDates.get(0).getDayOfMonth(), Integer.parseInt(expectedDay));
        assertEquals(birthdayDates.get(0).getMonthValue(), Integer.parseInt(expectedMonth));
        assertEquals(birthdayDates.get(0).getYear(), Integer.parseInt(expectedYar));
    }

    @Test
    void parseDate_shouldReturnThreeDatesForMondayFiveWeekdaysInAdvance() {
        // given
        LocalDate localDate = LocalDate.of(2020, 7, 20);

        // when
        List<LocalDate> birthdayDates = dateProvider.getBirthdayDates(localDate, 5);

        // then
        assertEquals(birthdayDates.size(), 3);
        assertEquals(birthdayDates.get(0).getDayOfMonth(), 25);
        assertEquals(birthdayDates.get(0).getMonthValue(), 7);
        assertEquals(birthdayDates.get(0).getYear(), 2020);
        assertEquals(birthdayDates.get(1).getDayOfMonth(), 26);
        assertEquals(birthdayDates.get(1).getMonthValue(), 7);
        assertEquals(birthdayDates.get(1).getYear(), 2020);
        assertEquals(birthdayDates.get(2).getDayOfMonth(), 27);
        assertEquals(birthdayDates.get(2).getMonthValue(), 7);
        assertEquals(birthdayDates.get(2).getYear(), 2020);
    }

    @ParameterizedTest
    @CsvSource({
            "21,7,2020,4,8,2020",
            "22,7,2020,5,8,2020",
            "23,7,2020,6,8,2020",
            "24,7,2020,7,8,2020"
    })
    void parseDate_shouldReturnSingleDateForTuesdayToFridayTenWeekdaysInAdvance(
            String day, String month, String year,
            String expectedDay, String expectedMonth, String expectedYar
    ) {
        // given
        LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        // when
        List<LocalDate> birthdayDates = dateProvider.getBirthdayDates(localDate, 10);

        // then
        assertEquals(birthdayDates.size(), 1);
        assertEquals(birthdayDates.get(0).getDayOfMonth(), Integer.parseInt(expectedDay));
        assertEquals(birthdayDates.get(0).getMonthValue(), Integer.parseInt(expectedMonth));
        assertEquals(birthdayDates.get(0).getYear(), Integer.parseInt(expectedYar));
    }

    @Test
    void parseDate_shouldReturnThreeDatesForMondayTenWeekdaysInAdvance() {
        // given
        LocalDate localDate = LocalDate.of(2020, 7, 20);

        // when
        List<LocalDate> birthdayDates = dateProvider.getBirthdayDates(localDate, 10);

        // then
        assertEquals(birthdayDates.size(), 3);
        assertEquals(birthdayDates.get(0).getDayOfMonth(), 1);
        assertEquals(birthdayDates.get(0).getMonthValue(), 8);
        assertEquals(birthdayDates.get(0).getYear(), 2020);
        assertEquals(birthdayDates.get(1).getDayOfMonth(), 2);
        assertEquals(birthdayDates.get(1).getMonthValue(), 8);
        assertEquals(birthdayDates.get(1).getYear(), 2020);
        assertEquals(birthdayDates.get(2).getDayOfMonth(), 3);
        assertEquals(birthdayDates.get(2).getMonthValue(), 8);
        assertEquals(birthdayDates.get(2).getYear(), 2020);
    }
}

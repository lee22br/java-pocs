package org.example;


import org.joda.time.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JodaTimeApiTest {

    @Test
    @DisplayName("Add days")
    void shouldAddDaysUsingJodaTime() {
        LocalDate startDate = new LocalDate(2026, 1, 1);

        LocalDate resultDate = startDate.plusDays(10);

        assertEquals(11, resultDate.getDayOfMonth());
        assertEquals(1, resultDate.getMonthOfYear());
    }

    @Test
    @DisplayName("Calculate the period")
    void periodsAndDurationsJodaTest() {
        LocalDate start = new LocalDate(2026, 1, 1);
        LocalDate end = new LocalDate(2027, 3, 15);

        Period period = new Period(start, end, PeriodType.yearMonthDay());

        assertEquals(1, period.getYears());
        assertEquals(2, period.getMonths());
        assertEquals(14, period.getDays());

        // DURATION: Absolute time in milliseconds
        DateTime moveStart = new DateTime(2026, 2, 26, 10, 0);
        DateTime moveEnd = new DateTime(2026, 2, 26, 12, 30);

        Duration duration = new Duration(moveStart, moveEnd);

        assertEquals(150, duration.getStandardMinutes());
        assertEquals(2, duration.getStandardHours());
    }

    @Test
    @DisplayName("Last day of month")
    void jodaPropertyTest() {
        LocalDate date = new LocalDate(2026, 2, 10);

        LocalDate lastDay = date.dayOfMonth().withMaximumValue();

        assertEquals(28, lastDay.getDayOfMonth()); // 2026 is not leap
    }

    @Test
    @DisplayName("Time Zones")
    void jodaTimeZoneTest() {

        DateTimeZone spZone = DateTimeZone.forID("America/Sao_Paulo");
        DateTimeZone laZone = DateTimeZone.forID("America/Los_Angeles");

        DateTime saoPauloTime = new DateTime(2026, 2, 26, 10, 0, spZone);

        DateTime losAngelesTime = saoPauloTime.withZone(laZone);

        assertEquals(5, losAngelesTime.getHourOfDay());
    }

    @Test
    @DisplayName("Leap Year logic")
    void jodaLeapYearTest() {
        LocalDate leapDate = new LocalDate(2024, 1, 1);
        LocalDate normalDate = new LocalDate(2026, 1, 1);

        assertTrue(leapDate.year().isLeap());
        assertFalse(normalDate.year().isLeap());
    }

    @Test
    @DisplayName("calculate between using Days/Hour/Month")
    void jodaDaysBetweenTest() {
        LocalDate d1 = new LocalDate(2026, 1, 1);
        LocalDate d2 = new LocalDate(2026, 3, 31);

        // Joda has specific classes for units (Days, Hours, Months)
        int daysBetween = Days.daysBetween(d1, d2).getDays();
        int hoursBetween = Hours.hoursBetween(d1, d2).getHours();
        int monthsBetween = Months.monthsBetween(d1, d2).getMonths();

        assertEquals(89, daysBetween);
        assertEquals(2136, hoursBetween);
        assertEquals(2, monthsBetween);
    }

}

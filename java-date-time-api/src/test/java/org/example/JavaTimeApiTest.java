package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class JavaTimeApiTest {

    @Test
    @DisplayName("Add days")
    void shouldAddDaysToLocalDate() {
        LocalDate startDate = LocalDate.of(2026, Month.JANUARY, 1);

        // When: Plus 10 days
        LocalDate resultDate = startDate.plusDays(10);

        // Then: result January 11th
        assertEquals(11, resultDate.getDayOfMonth(), "Day should be 11");
        assertEquals(Month.JANUARY, resultDate.getMonth(), "Month should be January");
        assertEquals(2026, resultDate.getYear(), "Year should be 2026");
    }

    @Test
    @DisplayName("Calculate the period ")
    void periodsAndDurationsTest() {

        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate end = LocalDate.of(2027, 3, 15);
        Period period = Period.between(start, end);

        assertEquals(1, period.getYears());
        assertEquals(2, period.getMonths());
        assertEquals(14, period.getDays());

        // DURATION: Time (Machine time)
        LocalTime moveStart = LocalTime.of(10, 0);
        LocalTime moveEnd = LocalTime.of(12, 30);
        Duration duration = Duration.between(moveStart, moveEnd);

        assertEquals(150, duration.toMinutes());
        assertEquals(2, duration.toHours());
        assertEquals(9000, duration.toSeconds());
    }

    @Test
    @DisplayName("TemporalAdjusters")
    void temporalAdjustersTest() {
        LocalDate date = LocalDate.of(2026, 2, 10); // February

        // Find the last day of the month
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        assertEquals(28, lastDay.getDayOfMonth()); // 2026 is not a leap year

        // Find the next Monday
        LocalDate nextMonday = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        assertEquals(16, nextMonday.getDayOfMonth());
    }

    @Test
    @DisplayName("Time Zones")
    void timeZoneTest() {
        LocalDateTime localTime = LocalDateTime.of(2026, 2, 26, 10, 0);

        //TimeZone
        ZonedDateTime saoPaulo = localTime.atZone(ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime losAngeles = saoPaulo.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));


        assertEquals(5, losAngeles.getHour());
    }

    @Test
    @DisplayName("Leap Year logic")
    void leapYearTest() {
        LocalDate leapYearDate = LocalDate.of(2024, 1, 1);
        LocalDate normalYearDate = LocalDate.of(2026, 1, 1);

        assertTrue(leapYearDate.isLeapYear());
        assertFalse(normalYearDate.isLeapYear());
    }

    @Test
    @DisplayName("calculate total days between using ChronoUnit")
    void chronoUnitTest() {
        LocalDate d1 = LocalDate.of(2026, 1, 1);
        LocalDate d2 = LocalDate.of(2026, 1, 31);

        long daysBetween = ChronoUnit.DAYS.between(d1, d2);

        assertEquals(30, daysBetween);
    }


}

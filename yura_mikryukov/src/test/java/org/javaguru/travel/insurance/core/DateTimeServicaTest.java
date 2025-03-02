package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class DateTimeServiceTest {

    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    void shouldCalculateDaysBetweenDates() {
        LocalDate dateFrom = LocalDate.of(2024, 10, 12);
        LocalDate dateTo = LocalDate.of(2025, 7, 13);

        long daysBetween = dateTimeService.calculateDaysBetween(dateFrom, dateTo);

        assertEquals(274, daysBetween);
    }

    @Test
    void shouldReturnZeroIfAnyDateIsNull() {
        LocalDate dateFrom = LocalDate.of(1900, 10, 1);
        LocalDate dateTo = null;

        long daysBetween = dateTimeService.calculateDaysBetween(dateFrom, dateTo);

        assertEquals(0, daysBetween);
    }
}
package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class DateTimeServicaTest {

    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    void shouldCalculateDaysBetweenDates() {
        Date dateFrom = new Date(2024,10,12);
        Date dateTo = new Date(2025, 07,13);

        long daysBetween = dateTimeService.calculateDaysBetween(dateFrom, dateTo);

        assertEquals(274,daysBetween);
    }

    @Test
    void shouldReturnZeroIfAnyDateIsNull() {
        Date dateFrom = new Date(2023 - 1900, 10, 1);
        Date dateTo = null;

        long daysBetween = dateTimeService.calculateDaysBetween(dateFrom, dateTo);

        assertEquals(0, daysBetween);
    }
}
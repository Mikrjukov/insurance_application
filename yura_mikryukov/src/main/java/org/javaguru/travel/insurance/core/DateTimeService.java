package org.javaguru.travel.insurance.core;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateTimeService {

    public long calculateDaysBetween(LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null || dateTo == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dateFrom, dateTo);
    }
}
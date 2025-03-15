package org.javaguru.travel.insurance.core;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeService {

    public long calculateDaysBetween(LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null || dateTo == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dateFrom, dateTo);
    }
}
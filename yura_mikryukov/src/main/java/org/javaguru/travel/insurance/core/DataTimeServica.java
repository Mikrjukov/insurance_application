package org.javaguru.travel.insurance.core;

import java.util.Date;
import java.time.ZoneId;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class DataTimeServica {

    public long calculateDaysBetween(Date dateFrom, Date dateTo) {
        if (dateFrom == null || dateTo == null) {
            return 0; // Если даты не указаны, возвращаем 0
        }



        LocalDate localDateFrom = dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateto = dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return ChronoUnit.DAYS.between(localDateFrom, localDateto);

    }
}

package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class UnderwritingServiceTest {

    private final DateTimeService dateTimeService = new DateTimeService();
    private final UnderwritingService underwritingService = new UnderwritingService(dateTimeService);

    @Test
    void shouldCalculatePremiumCorrectly() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.of(2020, 7, 13));
        request.setAgreementDateTo(LocalDate.of(2021, 7, 13));

        BigDecimal premium = underwritingService.calculatePremium(request);

        assertThat(premium).isEqualByComparingTo("365");
    }
//    @Test
//    void whenAgreementDateToIsNull() {
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
//        request.setAgreementDateFrom(LocalDate.of(2020,7,13));
//        request.setAgreementDateTo(null);
//
//        BigDecimal premium = underwritingService.calculatePremium(request);
//
//        assertThat(premium).isNull();
//    }
}
package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import java.util.Date;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void shouldSetCorrectValuesToResponse() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Yura");
        request.setPersonLastName("Mikryukov");
        request.setAgreementDateFrom(new Date(2025 , 1, 1));
        request.setAgreementDateTo(new Date(2025, 2, 20));

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();

    }

}
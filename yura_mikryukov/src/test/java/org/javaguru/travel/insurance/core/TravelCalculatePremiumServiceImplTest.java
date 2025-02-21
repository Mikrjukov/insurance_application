package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new TravelCalculatePremiumServiceImpl();
    }

    @Test
    public void shouldSetCorrectValuesToResponse() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Yura");
        request.setPersonLastName("Mikryukov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        TravelCalculatePremiumResponse response = service.calculatePremium(request);


        assertNotNull(response);
        assertEquals("Yura", response.getPersonFirstName());
        assertEquals("Mikryukov", response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());




    }

}






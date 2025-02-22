package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Yura");
        request.setPersonLastName("Mikryukov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
    }

    @Test
    void shouldSetCorrectValuesToResponse() {
        when(dateTimeService.calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()))
                .thenReturn(20L);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertNotNull(response);


        assertEquals("Yura", response.getPersonFirstName());
        assertEquals("Mikryukov", response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());

        assertEquals(new BigDecimal(20), response.getAgreementPrice());

        verify(dateTimeService, times(1))
                .calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }


}





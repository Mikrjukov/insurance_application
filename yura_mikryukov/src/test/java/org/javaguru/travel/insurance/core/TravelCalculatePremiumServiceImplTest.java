package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock private UnderwritingService premiumUnderwriting;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    private LocalDate createDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    @Test
    void shouldReturnResponseWithFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("personFirstName", response.getPersonFirstName());
    }

    @Test
    void shouldReturnResponseWithLastName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("personLastName", response.getPersonLastName());
    }

    @Test
    void shouldReturnResponseWithAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        LocalDate dateFrom = LocalDate.now();
        when(request.getAgreementDateFrom()).thenReturn(dateFrom);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(dateFrom, response.getAgreementDateFrom());
    }

    @Test
    void shouldReturnResponseWithAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        LocalDate dateTo = LocalDate.now();
        when(request.getAgreementDateTo()).thenReturn(dateTo);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(dateTo, response.getAgreementDateTo());
    }

    @Test
    void shouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errorList = List.of(new ValidationError("field", "errorMessage"));
        when(requestValidator.validate(request)).thenReturn(errorList);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }
    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(new BigDecimal(9));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), new BigDecimal(9));
    }




}




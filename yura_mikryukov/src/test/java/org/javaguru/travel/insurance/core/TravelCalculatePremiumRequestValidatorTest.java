package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumRequestValidatorTest {


    private final TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();


    @Test
    void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Last");
        when(request.getAgreementDateFrom()).thenReturn(new Date());

        List<ValidationError> errors = requestValidator.validate(request);

        assertThat(errors).hasSize(1)
                .extracting(ValidationError::getField)
                .containsExactly("personFirstName");
    }

    @Test
    void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("First");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(new Date());

        List<ValidationError> errors = requestValidator.validate(request);

        assertThat(errors).hasSize(1)
                .extracting(ValidationError::getField)
                .containsExactly("personLastName");
    }

    @Test
    void shouldReturnErrorWhenAgreementDateFromIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("First");
        when(request.getPersonLastName()).thenReturn("Last");
        when(request.getAgreementDateFrom()).thenReturn(null);

        List<ValidationError> errors = requestValidator.validate(request);

        assertThat(errors).hasSize(1)
                .extracting(ValidationError::getField)
                .containsExactly("AgreementDateFrom");
    }
}

package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now().plusDays(1));

        List<ValidationError> errors = requestValidator.validate(request);
        errors.forEach(error -> System.out.println("Field: " + error.getField() + ", Message: " + error.getMessage()));


        assertThat(errors).hasSize(1)
                .extracting(ValidationError::getField)
                .containsExactly("personFirstName");
    }

    @Test
    void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("First");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now().plusDays(1));

        List<ValidationError> errors = requestValidator.validate(request);
        errors.forEach(error -> System.out.println("Field: " + error.getField() + ", Message: " + error.getMessage()));


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
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now());

        List<ValidationError> errors = requestValidator.validate(request);

        assertThat(errors).hasSize(1)
                .extracting(ValidationError::getField)
                .containsExactly("AgreementDateFrom");
    }
    @Test
    void shouldReturnErrorWhenAgreementDateToIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("First");
        when(request.getPersonLastName()).thenReturn("Last");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(null);


        List<ValidationError> errors = requestValidator.validate(request);

        assertThat(errors).hasSize(1)
                .extracting(ValidationError::getField)
                .containsExactly("AgreementDateTo");
    }
    @Test
    void shouldReturnErrorWhenAgreementDateToIsBeforeAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now().minusDays(1));

        List<ValidationError> errors = requestValidator.validate(request);


        assertThat(errors).anyMatch(error ->
                error.getField().equals("agreementDateTo") &&
                        error.getMessage().equals("Must be after agreementDateFrom")
        );
    }

}

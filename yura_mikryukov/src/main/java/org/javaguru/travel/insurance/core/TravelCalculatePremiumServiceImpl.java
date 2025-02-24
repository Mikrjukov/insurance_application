package org.javaguru.travel.insurance.core;

import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final DateTimeService dateTimeService;
    private final TravelCalculatePremiumRequestValidator requestValidator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        List<ValidationError> errors = requestValidator.validate(request);
        if (!errors.isEmpty()) {
            return new TravelCalculatePremiumResponse(errors);
        }

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());

        long daysBetween = dateTimeService.calculateDaysBetween(
                response.getAgreementDateFrom(),
                response.getAgreementDateTo());

        response.setAgreementPrice(new BigDecimal(daysBetween));
        return response;
    }
}


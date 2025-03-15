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

public class TravelCalculatePremiumServiceImpl extends TravelCalculatePremiumService {

    private final UnderwritingService premiumUnderwriting;
    private final TravelCalculatePremiumRequestValidator requestValidator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);

        if (!errors.isEmpty()) {
            return buildPremiumResponseErrorList(errors);
        }
        BigDecimal premium = premiumUnderwriting.calculatePremium(request);
        return buildPremiumResponse(request);
    }



    private TravelCalculatePremiumResponse buildPremiumResponse(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        return response;
    }

    private TravelCalculatePremiumResponse buildPremiumResponseErrorList(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }
}
package org.javaguru.travel.insurance.core;

import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final DateTimeService dateTimeService;



    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
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


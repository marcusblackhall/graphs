package com.iamatum.iamatumgraphs.web.validator;

import com.iamatum.iamatumgraphs.web.request.BetweenDatesParameters;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class LcpsValidator implements Validator {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean supports(Class<?> clazz) {
        return BetweenDatesParameters.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        BetweenDatesParameters betweenDatesParameters = (BetweenDatesParameters) target;

        boolean matches = validDate(betweenDatesParameters.getStartDate());
        if (!matches) {
            errors.reject("startDate", "Must be in format yyyy-MM-dd");
        }
        boolean endDateValid = validDate(betweenDatesParameters.getEnddate());
        if (!endDateValid) {
            errors.reject("endDate", "Must be in format yyyy-MM-dd");
        }

    }

    private boolean validDate(String enddate) {
        try {
            dateTimeFormatter.parse(enddate);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}

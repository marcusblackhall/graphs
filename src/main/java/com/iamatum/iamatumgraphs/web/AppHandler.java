package com.iamatum.iamatumgraphs.web;

import com.iamatum.iamatumgraphs.domain.Lcps;
import com.iamatum.iamatumgraphs.model.LcpsData;
import com.iamatum.iamatumgraphs.services.LcpsService;
import com.iamatum.iamatumgraphs.web.request.BetweenDatesParameters;
import com.iamatum.iamatumgraphs.web.validator.LcpsValidator;
import org.apache.commons.collections4.MultiValuedMap;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class AppHandler {

    private final LcpsService lcpsService;
    private final LcpsValidator validator;

    public AppHandler(LcpsService lcpsService,LcpsValidator validator){
        this.lcpsService = lcpsService;
        this.validator = validator;
    }

    public Mono<ServerResponse> lcpsBetweenDates(ServerRequest serverRequest){
        String startDate = serverRequest.queryParam("startDate").orElse(null);
        String endDate = serverRequest.queryParam("endDate").orElse(null);

        BetweenDatesParameters betweenDatesParameters = new BetweenDatesParameters(startDate, endDate);
        Errors errors = new BeanPropertyBindingResult(
                betweenDatesParameters,
                BetweenDatesParameters.class.getName());
        validator.validate(betweenDatesParameters, errors);

        if (errors.hasErrors()){
            return ServerResponse.badRequest().build();
        }

        return ServerResponse.ok().bodyValue(lcpsDataBetweenDates(startDate, endDate));

    }

    private List<LcpsData> lcpsDataBetweenDates(String startDate, String endDate) {
        return lcpsService.lcpsBetweenDates(LocalDate.parse(startDate), LocalDate.parse(endDate));

    }

}

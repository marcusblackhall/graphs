package com.iamatum.iamatumgraphs.web;

import com.iamatum.iamatumgraphs.model.LcpsData;
import com.iamatum.iamatumgraphs.services.LcpsService;
import com.iamatum.iamatumgraphs.web.request.BetweenDatesParameters;
import com.iamatum.iamatumgraphs.web.validator.LcpsValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component

public class AppHandler {

    private final LcpsService lcpsService;
    private final LcpsValidator validator;

    public AppHandler(LcpsService lcpsService, LcpsValidator validator) {
        this.lcpsService = lcpsService;
        this.validator = validator;
    }

    public Mono<ServerResponse> lcpsBetweenDates(ServerRequest serverRequest) {
        String startDate = serverRequest.queryParam("startDate").orElse(null);
        String endDate = serverRequest.queryParam("endDate").orElse(null);

        BetweenDatesParameters betweenDatesParameters = new BetweenDatesParameters(startDate, endDate);
        Errors errors = new BeanPropertyBindingResult(
                betweenDatesParameters,
                BetweenDatesParameters.class.getName());
        validator.validate(betweenDatesParameters, errors);

        if (errors.hasErrors()) {
            return ServerResponse.badRequest().build();
        }

        return ServerResponse.ok().bodyValue(lcpsDataBetweenDates(startDate, endDate));

    }

    private List<LcpsData> lcpsDataBetweenDates(String startDate, String endDate) {
        return lcpsService.lcpsBetweenDates(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    public Mono<ServerResponse> homepage(ServerRequest serverRequest) {
        return ServerResponse.ok().render("index", Map.of("left", "card"));
    }

    public Mono<ServerResponse> lcpspage(ServerRequest serverRequest) {
        return ServerResponse.ok().render("index", Map.of("left", "lcps"));
    }

    public Mono<ServerResponse> lcpstable(ServerRequest serverRequest) {
        return ServerResponse.ok().render("index", Map.of("left", "lcpstable"));
    }
}

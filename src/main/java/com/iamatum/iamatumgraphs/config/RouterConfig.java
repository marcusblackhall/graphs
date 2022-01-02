package com.iamatum.iamatumgraphs.config;

import com.iamatum.iamatumgraphs.web.AppHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> appRoutes(AppHandler appHandler) {

        return route()
                .GET("/api/v2/lcps/betweenDates",
                        accept(MediaType.APPLICATION_JSON),
                        appHandler::lcpsBetweenDates
                )
                .GET("/home", appHandler::homepage)
                .GET("/lcps", appHandler::lcpspage)
                .GET("/lcpstable", appHandler::lcpstable)
                .GET("/", appHandler::homepage)
                .build();

    }


}

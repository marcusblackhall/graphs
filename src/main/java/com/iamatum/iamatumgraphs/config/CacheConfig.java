package com.iamatum.iamatumgraphs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig implements WebFluxConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/js/*.js")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));

//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("/public", "classpath:/static/")
//                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }
}

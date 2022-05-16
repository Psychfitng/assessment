package com.irespond.api;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .useDefaultResponseMessages(false);
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "iRespond REST API",
                "Some custom description of IRESPOND ASSESSMENT API.",
                "1",
                "Terms of service",
                new Contact("Bolaji Adams", "www.irespond.africa", "bolaji@irespond.africa"),
                "License of API", "API license URL", Collections.emptyList());
    }
}


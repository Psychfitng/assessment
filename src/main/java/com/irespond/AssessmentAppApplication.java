package com.irespond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class AssessmentAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssessmentAppApplication.class, args);
    }


}

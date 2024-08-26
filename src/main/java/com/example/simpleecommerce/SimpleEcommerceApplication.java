package com.example.simpleecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleEcommerceApplication.class, args);
    }

}

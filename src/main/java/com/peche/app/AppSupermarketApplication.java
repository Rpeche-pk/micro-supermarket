package com.peche.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppSupermarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSupermarketApplication.class, args);
    }

}

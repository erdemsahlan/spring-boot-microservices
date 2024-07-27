package com.autenticationservice.autenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class AutenticationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutenticationServiceApplication.class, args);
    }

}

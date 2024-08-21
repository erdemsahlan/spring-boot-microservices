package com.autenticationservice.autenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component("com.autenticationservice.autenticationservice")
public class AutenticationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutenticationServiceApplication.class, args);
    }

}

package com.homeproject.homeprojectfortrainingspringframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.homeproject.homeprojectfortrainingspringframework"})
@EnableJpaRepositories(basePackages = {"com.homeproject.homeprojectfortrainingspringframework"})
@EntityScan(basePackages = {"com.homeproject.homeprojectfortrainingspringframework"})

public class HomeProjectForTrainingSpringFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeProjectForTrainingSpringFrameworkApplication.class, args);
    }

}

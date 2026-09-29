package it.unibo.citycare.solutions2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class SolutionsApp {

    public static void main(String[] args) {
        SpringApplication.run(SolutionsApp.class, args);
    }
}
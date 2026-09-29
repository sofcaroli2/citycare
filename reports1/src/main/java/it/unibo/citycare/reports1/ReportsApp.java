package it.unibo.citycare.reports1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class ReportsApp {

    public static void main(String[] args) {
        SpringApplication.run(ReportsApp.class, args);
    }
}
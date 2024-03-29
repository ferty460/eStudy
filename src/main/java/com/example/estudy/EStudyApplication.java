package com.example.estudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EStudyApplication.class, args);
    }

}

package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example", "com.examplefood"})
public class SpringfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringfoodApplication.class, args);
    }

}
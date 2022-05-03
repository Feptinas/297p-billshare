package com.example.springbootawsexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootAwsExampleApplication {
    @GetMapping("/")
    public String home() {
        return "Welcome to AWS First Deployment....!!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAwsExampleApplication.class, args);
    }

}

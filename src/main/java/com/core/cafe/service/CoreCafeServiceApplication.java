package com.core.cafe.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class CoreCafeServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CoreCafeServiceApplication.class,args);
    }
}

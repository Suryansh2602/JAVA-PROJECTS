package com.info.urbaneats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UrbanEatsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanEatsEurekaServerApplication.class, args);
		System.out.println(" UrabanEats eureka Server Started ");
	}

}

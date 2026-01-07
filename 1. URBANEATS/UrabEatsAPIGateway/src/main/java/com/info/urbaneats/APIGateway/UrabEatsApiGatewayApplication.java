package com.info.urbaneats.APIGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UrabEatsApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrabEatsApiGatewayApplication.class, args);
		System.out.println("API Gateway Started");
	}

}

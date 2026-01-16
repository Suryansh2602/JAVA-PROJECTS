package com.info.urbaneats.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UrbanEatsBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanEatsBookingServiceApplication.class, args);
	}

}

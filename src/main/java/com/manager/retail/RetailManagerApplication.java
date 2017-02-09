package com.manager.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.google.maps.GeoApiContext;

@EnableAsync
@SpringBootApplication
public class RetailManagerApplication {
	
	@Bean
	public GeoApiContext getGeoApiContext(){
		return new GeoApiContext();
	}

	public static void main(String[] args) {
		SpringApplication.run(RetailManagerApplication.class, args);
	}
}

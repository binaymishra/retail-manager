package com.manager.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.google.maps.GeoApiContext;

/**
 * @author Binay Mishra
 * 
 * This class conatins the main() method to bootup application
 * with the help of an embedded tomcat server
 */
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

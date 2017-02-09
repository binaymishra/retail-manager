package com.manager.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.google.maps.GeoApiContext;

@EnableAsync
@SpringBootApplication
public class RetailManagerApplication {
	

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
	    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	    taskExecutor.setMaxPoolSize(10);
	    taskExecutor.afterPropertiesSet();
	    return taskExecutor;
	}
	
	@Bean 
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public GeoApiContext getGeoApiContext(){
		return new GeoApiContext();
	}

	public static void main(String[] args) {
		SpringApplication.run(RetailManagerApplication.class, args);
	}
}

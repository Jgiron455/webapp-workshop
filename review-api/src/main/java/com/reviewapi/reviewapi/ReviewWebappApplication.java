package com.reviewapi.reviewapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ReviewWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewWebappApplication.class, args);
	}

}

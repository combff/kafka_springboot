package com.encore.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PubSubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubSubApplication.class, args);
	}

}

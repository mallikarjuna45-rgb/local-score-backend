package com.cricket.local_score;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LocalScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalScoreApplication.class, args);
	}
	

}

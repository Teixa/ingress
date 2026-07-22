package com.teixaa.venues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VenuesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VenuesApplication.class, args);
	}

}

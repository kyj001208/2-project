package com.green.petfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableCaching
public class PetfirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetfirstApplication.class, args);
	}
	
	@Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder(13);
	 }

	
	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}

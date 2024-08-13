package com.food.swigato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SwigatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwigatoApplication.class, args);
	}
	
@Bean
RestTemplate restTemplate() {
	return new RestTemplate();
}

}

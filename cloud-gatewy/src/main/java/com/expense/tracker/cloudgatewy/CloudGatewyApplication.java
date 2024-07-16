package com.expense.tracker.cloudgatewy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewyApplication.class, args);
	}

}

package com.expense.tracker.cloudgatewy;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class CloudGatewyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewyApplication.class, args);
	}
	
    @GetMapping
    public String welcome() {
        return "Welcome to Google !!";
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        System.out.println("username : " + principal.getName());
        return principal;
    }
    @PostMapping("/user")
    public String userS() {
       // System.out.println("username : " + principal.getName());
        return "principal";
    }

}

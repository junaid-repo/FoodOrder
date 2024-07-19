package com.food.customers.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.customers.entities.CustomerDetails;
import com.food.customers.services.UserService;

@RestController
@RequestMapping("/customer")
public class CController {
	
	@Autowired
	UserService serv;

	@GetMapping
	public String welcome(Principal principal) {
		return "welcome to my app ";
	}

	@GetMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
	@PostMapping("/addDetails")
	ResponseEntity<String> addUserDetails(@RequestBody CustomerDetails request){
		String response = serv.addUserDetails(request);
		return ResponseEntity.status(HttpStatus.CREATED).body("username for the created user is "+response);
	}

}

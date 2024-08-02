package com.food.customers.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.customers.entities.CustomerAddress;
import com.food.customers.entities.CustomerDetails;
import com.food.customers.services.UserService;

import jakarta.validation.Valid;

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
	ResponseEntity<String> addUserDetails(@Valid @RequestBody CustomerDetails request){
		String response = serv.addUserDetails(request);
		return ResponseEntity.status(HttpStatus.CREATED).body("username for the created user is "+response);
	}
	@GetMapping("/view/customerDetails")
	ResponseEntity<CustomerDetails> viewCustomerDetails(@RequestParam String userCode){
		
		CustomerDetails response = serv.viewCustomerDetails(userCode);
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}
	@PostMapping("/addDeliveryAddress")
	ResponseEntity<String> addAddress(@Valid @RequestBody CustomerAddress request){
		String response = serv.addAddress(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@GetMapping("/viewAddresses")
	ResponseEntity<List<CustomerAddress>> viewAddress(@RequestParam String customerCode){
		List<CustomerAddress> response = serv.viewAddress(customerCode);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@GetMapping("/view/address")
	ResponseEntity<CustomerAddress> getAddressById(@RequestParam String id){
		return ResponseEntity.status(HttpStatus.FOUND).body(serv.findAddressById(id));
	}
}

package com.food.riders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.riders.entity.RiderInfo;
import com.food.riders.services.RiderServices;

@RestController
@RequestMapping("/riders")
public class RiController {
	@Autowired
	RiderServices serv;
	@PostMapping("/register")
	ResponseEntity<String> registerRider(@RequestBody RiderInfo request){
		
		String riderCode=serv.registerRider(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(riderCode);
		
		
	}

}

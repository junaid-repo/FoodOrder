package com.food.riders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.riders.entity.RiderInfo;
import com.food.riders.repos.RiderRepository;

@Service
public class RiderServices {
	
	@Autowired
	RiderRepository riderRepo;

	public String registerRider(RiderInfo request) {
		
		riderRepo.save(request);
		
		return "RI0032";
	}

}

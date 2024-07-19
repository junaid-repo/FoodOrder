package com.food.customers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.customers.entities.CustomerDetails;
import com.food.customers.repo.CustomerRepository;

@Service
public class UserService {

	@Autowired
	CustomerRepository crepo;

	public String addUserDetails(CustomerDetails request) {

		CustomerDetails cd = crepo.save(request);

		if (cd.getId() != null) {
			request.setUsername("USR001" + cd.getId());
			crepo.save(request);
			return request.getUsername();
		}

		return "some error";
	}

}

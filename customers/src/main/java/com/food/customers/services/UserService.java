package com.food.customers.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.customers.entities.CustomerAddress;
import com.food.customers.entities.CustomerDetails;
import com.food.customers.repo.CustomerAddressRepository;
import com.food.customers.repo.CustomerRepository;
import com.food.customers.services.utility.Utility;

@Service
public class UserService {

	@Autowired
	CustomerRepository crepo;

	@Autowired
	CustomerAddressRepository carepo;

	public String addUserDetails(CustomerDetails request) {

		CustomerDetails cd = crepo.save(request);

		if (cd.getId() != null) {
			request.setUsername("USR001" + cd.getId());
			crepo.save(request);
			return request.getUsername();
		}

		return "some error";
	}

	public String addAddress(CustomerAddress request) {
		
	

		request.setCreatedDate(LocalDateTime.now());
		request.setUpdatedDate(LocalDateTime.now());
		
		try {
			Map<String, String> currentAddress = Utility.getCurrentAddress();

			request.setCity(currentAddress.get("city"));
			request.setState(currentAddress.get("regionName"));
			request.setPincode(currentAddress.get("zip"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		carepo.save(request);
		return "address saved";
	}

	public List<CustomerAddress> viewAddress(String customerCode) {
		List<CustomerAddress> response = carepo.getAddressList(customerCode);
		return response;
	}

	public CustomerAddress findAddressById(String id) {
		return carepo.findById(Integer.parseInt(id)).get();
	}

	public CustomerDetails viewCustomerDetails(String userCode) {
		
		return crepo.findByCustomerCode(userCode);
	}

}

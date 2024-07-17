package com.food.resturants.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.resturants.entities.Address;
import com.food.resturants.entities.ResturantMenu;
import com.food.resturants.entities.ResturantProfile;
import com.food.resturants.entities.ResturantsDetails;
import com.food.resturants.repo.ResturantMenuRepository;
import com.food.resturants.repo.ResturantProfileRepository;
import com.food.resturants.repo.ResturantRepository;
import com.food.resturants.utility.Utility;

import jakarta.validation.Valid;

@Service
public class RService {

	@Autowired
	ResturantRepository rrepo;

	@Autowired
	ResturantProfileRepository rprepo;
	
	@Autowired
	ResturantMenuRepository rmrepo;

	public Map<String, Object> registerResturants(ResturantsDetails request) {
		Map<String, Object> retMap = new HashMap<>();
		LocalDateTime relatedDate = LocalDateTime.now();
		try {
			Map<String, String> currentAddress = Utility.getCurrentAddress();

			request.getAddress().setCity(currentAddress.get("city"));
			request.getAddress().setState(currentAddress.get("regionName"));
			request.getAddress().setPincode(currentAddress.get("zip"));
			request.getAddress().setCreatedDate(relatedDate);
			request.getAddress().setUpdatedDate(relatedDate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setCreatedDate(relatedDate);
		request.setUpdatedDate(relatedDate);
		ResturantsDetails res = rrepo.save(request);

		String resName = "RES004" + String.valueOf(res.getId());

		try {
			rrepo.updateResturantCode(res.getId(), resName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		retMap.put("resturantCode", resName);
		retMap.put("status", "Success");

		return retMap;
	}

	public String addResturantProfile(ResturantProfile request) {
		ResturantProfile response = new ResturantProfile();
		response = rprepo.save(request);
		if (response.getId()!= null) {
			return "Details Added";
		}
		return null;
	}

	public String addMenuItems(ResturantMenu request) {
		ResturantMenu response= new ResturantMenu();
		response = rmrepo.save(request);
		if (response.getId() != null) {
			return "Details Added";
		}
		return null;
	}

}

package com.food.resturants.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
		if (response.getId() != null) {
			return "Details Added";
		}
		return null;
	}

	public String addMenuItems(ResturantMenu request) {
		ResturantMenu response = new ResturantMenu();
		response = rmrepo.save(request);
		if (response.getId() != null) {
			return "Details Added";
		}
		return null;
	}

	public List<String> saveBulkResurants(Map<String, String> fileLocation) throws IOException {
		List<String> response = new ArrayList<>();
		String resturantLocation = fileLocation.get("rl");
		String addressLocation = fileLocation.get("al");
		Map<String, Object> fileMap = Utility.getListOfObjects(resturantLocation, addressLocation);

		List<ResturantsDetails> rd = (List<ResturantsDetails>) fileMap.get("resturantDetails");
		List<Address> ad = (List<Address>) fileMap.get("addressDetails");

		Map<String, Integer> result = new HashMap<>();

		Iterator<ResturantsDetails> restDetails = rd.iterator();
		Iterator<Address> addDetails = ad.iterator();
		while (restDetails.hasNext() && addDetails.hasNext()) {
			restDetails.next().setAddress(addDetails.next());
			// Map<String, Object> responseMap = registerResturants(restDetails.next());
			// response.add((String) responseMap.get("resturantCode"));
		}

		for (int i = 0; i < rd.size(); i++) {
			rd.get(i).setAddress(ad.get(i));
		}
		System.out.println(rd.toString());

		rd.stream().forEach(obj -> {
			Map<String, Object> responseMap = registerResturants(obj);
			response.add((String) responseMap.get("resturantCode"));
		});

		return response;
	}

	public List<String> saveBulkAddMenuItmes(Map<String, String> filesLocations) throws IOException {
		List<String> response = new ArrayList<>();
		String menuItemFileLocation = filesLocations.get("menuItemFile");
		Map<String, Object> fileMap = Utility.getListOfObjectsForMenu(menuItemFileLocation);
		List<ResturantMenu> menuDetails = (List<ResturantMenu>) fileMap.get("resturantMenuDetails");

		menuDetails.stream().forEach(menu -> {
			String menuDetailsResponse = addMenuItems(menu);
			response.add(menuDetailsResponse);
		});
		return response;
	}

}

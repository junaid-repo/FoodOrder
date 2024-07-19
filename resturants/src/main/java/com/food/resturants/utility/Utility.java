package com.food.resturants.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.resturants.entities.Address;
import com.food.resturants.entities.ResturantsDetails;

public class Utility {

	public static Map<String, String> getCurrentAddress() throws IOException {

		Map<String, String> locationDetails = new HashMap<>();
		String location;
		String ipAddress = getIp();
		String url = "http://ip-api.com/json/" + getIp();
		URL whatismylocation = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(whatismylocation.openStream()));

		location = in.readLine(); // you get the IP as a String
		System.out.println(location);

		Map<String, String> result = new ObjectMapper().readValue(location, HashMap.class);

		return result;

	}

	public static String getIp() throws IOException {
		String ip = "";
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));

			ip = in.readLine(); // you get the IP as a String
			System.out.println(ip);
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return ip;
	}

	public static Map<String, Object> getListOfObjects(String rl, String al) throws IOException {
		List<ResturantsDetails> rd = draftResurantDetails(rl);
		List<Address> ad = draftResturantAddress(al);

		Map<String, Object> retMap = new HashMap<>();

		retMap.put("resturantDetails", rd);
		retMap.put("addressDetails", ad);

		return retMap;
	}

	public static List<ResturantsDetails> draftResurantDetails(String fileIn) throws IOException {

		List<ResturantsDetails> studentList = new ArrayList<>();
		// String fileIn = "/home/junaid/Documents/resturantRegister.csv";
		String line = null;

		FileReader fileReader = new FileReader(fileIn);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int count = 0;
		while ((line = bufferedReader.readLine()) != null) {
			count++;
			if (count > 1) {
				try {
					String[] temp = line.split(",");
					String name = temp[0];
					String ownerName = temp[1];
					String gstNumber = temp[2];
					String contactNumber = temp[3];

					var resturantDetails = ResturantsDetails.builder().name(name).ownerName(ownerName)
							.gstNumber(gstNumber).contactNumber(contactNumber).build();
					;
					studentList.add(resturantDetails);

				} catch (Exception e) {
					// TODO Auto-generated catch block

				}
			}

		}
		bufferedReader.close();
		return studentList;

	}

	private static List<Address> draftResturantAddress(String fileIn) throws IOException {

		List<Address> addressList = new ArrayList<>();
		// String fileIn = "/home/junaid/Documents/resturantAddress.csv";
		String line = null;

		FileReader fileReader = new FileReader(fileIn);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int count = 0;
		while ((line = bufferedReader.readLine()) != null) {
			count++;
			if (count > 1) {
				try {
					String[] temp = line.split("##");
					String area = temp[0];

					var address = Address.builder().area(area).build();
					;
					addressList.add(address);

				} catch (Exception e) {
					// TODO Auto-generated catch block

				}
			}

		}
		bufferedReader.close();
		return addressList;

	}
}

package com.food.customers.services.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

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
}

package com.food.swigato.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiCalls {

	@Autowired
	RestTemplate template;

	public Map<String, Object> getProductDetails(String id) {
		// List<GroupMembers> memberList= new ArrayList<>();

		String uri = "http://" + "localhost:7070" + "/resturant/get/foodDetails/";
		ResponseEntity<Map<String, Object>> foodDetails = template.exchange(uri + id, HttpMethod.GET,
				new HttpEntity<>(httpHeader()), new ParameterizedTypeReference<Map<String, Object>>() {
				});

		return foodDetails.getBody();

	}

	private HttpHeaders httpHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		// httpHeaders.add("Authorization", "Basic "+getBasicAuthHeader());
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return httpHeaders;
	}

	public Map<String, Object> getAddress(Integer id) {
		String uri = "http://" + "host.docker.internal:7072" + "/customer/view/address?id=";
		String finalUri=uri+id;
		System.out.println(finalUri);
		ResponseEntity<Map<String, Object>> addressDetails = template.exchange(uri + id, HttpMethod.GET,
				new HttpEntity<>(httpHeader()), new ParameterizedTypeReference<Map<String, Object>>() {
				});
		return addressDetails.getBody();
	}
	public Map<String, Object> getCustomerDetails(String userCode) {
		String uri = "http://" + "host.docker.internal:7072" + "/customer/view/customerDetails?userCode=";
		String finalUri=uri+userCode;
		System.out.println(finalUri);
		ResponseEntity<Map<String, Object>> addressDetails = template.exchange(uri + userCode, HttpMethod.GET,
				new HttpEntity<>(httpHeader()), new ParameterizedTypeReference<Map<String, Object>>() {
				});
		return addressDetails.getBody();
	}

}

package com.food.swigato.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.food.swigato.api.ApiCalls;
import com.food.swigato.api.EmailSender;
import com.food.swigato.dto.AddressDTO;
import com.food.swigato.dto.CartDTO;
import com.food.swigato.dto.CartSummary;
import com.food.swigato.dto.FoodDetails;
import com.food.swigato.dto.FoodDetailsDTO;
import com.food.swigato.entities.BillDetails;
import com.food.swigato.repos.CartSummaryRepository;
import com.food.swigato.repos.FoodDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppService {

	@Autowired
	ApiCalls api;

	@Autowired
	CartSummaryRepository cartRepo;

	@Autowired
	FoodDetailsRepository foodRepo;
	
	@Autowired
	EmailSender emailSender;

	@Value("${charges.gstPercentage}")
	private String gst;
	@Value("${charges.deliveryCharge}")
	private String deliveryFee;
	@Value("${charges.platformFee}")
	private String platformFee;
	@Value("${charges.extraCharge}")
	private String extraCharge;

	public CartSummary selectFoodAndAdd(String customerCode, String addressId, String productId, String quantity) {

		// fetch foodDetails from productId

		// fetch customerDetails from customerId

		// fetch address from addressId Id;

		var cartDetails = CartSummary.builder().id(customerCode).customerCode(customerCode)
				.addressId(Integer.parseInt(addressId)).build();
		CartSummary response = cartRepo.save(cartDetails);

		Map<String, Object> productDetails = api.getProductDetails(productId);
		List<FoodDetails> productList = List.of(FoodDetails.builder().foodName((String) productDetails.get("name"))
				.quantity(Integer.parseInt(quantity)).basePrice((Double) productDetails.get("price"))
				.cartId(response.getId()).foodId((Integer) productDetails.get("id")).build());

		productList.stream().forEach(food -> foodRepo.save(food));

		return response;
	}

	public CartDTO getCartDetails(String customerCode) {

		CartSummary cart = cartRepo.findById(customerCode).get();
		Map<String, Object> addressDetails = api.getAddress(cart.getAddressId());
		List<FoodDetailsDTO> foodLists = new ArrayList<>();

		var address = AddressDTO.builder().area((String) addressDetails.get("area"))
				.city((String) addressDetails.get("city")).state((String) addressDetails.get("state"))
				.pincode((String) addressDetails.get("pincode")).build();

		List<FoodDetails> foodDetailsFromRepo = foodRepo.findByCartId(cart.getId());

		foodDetailsFromRepo.stream().forEach(food -> {
			var foodObj = FoodDetailsDTO.builder().foodName(food.getFoodName())
					.cost(food.getBasePrice() * (food.getQuantity())).count(String.valueOf(food.getQuantity())).build();
			foodLists.add(foodObj);
		});

		BillDetails billDetails = getTotalAmount(foodLists);

		var response = CartDTO.builder().address(address).itemLists(foodLists).billDetails(billDetails).build();

		return response;
	}

	private BillDetails getTotalAmount(List<FoodDetailsDTO> foodLists) {

		Double amountCalculated = 0d;

		for (FoodDetailsDTO food : foodLists) {
			amountCalculated = amountCalculated + (food.getCost() * Integer.parseInt(food.getCount()));
		}
		Double totalGst = amountCalculated * Double.parseDouble(gst) / 100d;
		Double grandTotal = amountCalculated + totalGst + Double.parseDouble(deliveryFee)
				+ Double.parseDouble(extraCharge) + Double.parseDouble(platformFee);

		var billDetails = BillDetails.builder().itemTotal(amountCalculated).gstAndResturantCharges(totalGst)
				.deliveryCharge(Double.parseDouble(deliveryFee)).extraCharges(Double.parseDouble(extraCharge))
				.platformFee(Double.parseDouble(platformFee)).grandTotal(grandTotal).build();
		return billDetails;

	}

	public String doCheckout(String cartId, Double amount) {

		Map<String, Object> customerDetails = api.getCustomerDetails(cartId);
		String emailId = (String) customerDetails.get("emailId");
		String name = (String) customerDetails.get("name");
		log.info("The email of the customer-->" + emailId);
		

		if (emailId.equals("na@na.com")) {
			emailId = "junaidraza3002@gmail.com";
		}
		emailSender.sendEmailForOrderConfirmations(emailId, name, amount);
		//sendEmailToCustomer(emailId, amount, name);

		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();

		return uuidAsString;
	}

	/*
	 * @Async("asyncTaksExe") public void sendEmailToCustomer(String emailId, Double
	 * amount, String name) {
	 * 
	 * 
	 * if (emailId.equals("na@na.com")) { emailId = "junaidraza3002@gmail.com"; }
	 * 
	 * //return CompletableFuture.completedFuture("email sent successfull"); }
	 */

}

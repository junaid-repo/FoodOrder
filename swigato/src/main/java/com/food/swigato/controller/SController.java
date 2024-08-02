package com.food.swigato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.swigato.dto.CartDTO;
import com.food.swigato.dto.CartSummary;
import com.food.swigato.service.AppService;

@RestController
@RequestMapping("/swigato")
public class SController {

	@Autowired
	AppService serv;

	// SearchFoodResturant

	// Select Food and Add to cart
	@PostMapping("/addItemToCart")
	ResponseEntity<CartSummary> selectFoodAndAdd(@RequestParam String customerCode, @RequestParam String addressId,
			@RequestParam String productId, @RequestParam String productQuantity) {

		CartSummary response = serv.selectFoodAndAdd(customerCode, addressId, productId, productQuantity);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/cartSummary")
	ResponseEntity<CartDTO> getCartDetails(@RequestParam String customerCode){
		
		CartDTO cartResponse = serv.getCartDetails(customerCode);
		
		return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
		
	}
	@PostMapping("/checkout")
	ResponseEntity<String> doCheckout(@RequestParam String cartId, @RequestParam Double amount){
		
		String response=serv.doCheckout(cartId, amount);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	// Cart Summary

	// Select Delivery Address

	// view order summary and place order

}

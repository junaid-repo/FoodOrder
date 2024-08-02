package com.food.swigato.dto;


import java.util.List;

import com.food.swigato.entities.BillDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	
	List<FoodDetailsDTO> itemLists;
	AddressDTO address;
	BillDetails billDetails;

}

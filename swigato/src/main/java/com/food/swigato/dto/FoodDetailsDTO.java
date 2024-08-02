package com.food.swigato.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDetailsDTO {
	
	private String foodName;
	private String count;
	private Double cost;

}

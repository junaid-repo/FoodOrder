package com.food.customers.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="customer_address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Integer id;

	@NotBlank(message="customerCode cannot be blank")
	@Column(name= "customerCode")
	String customerCode;
	
	@NotBlank(message="area cannot be blank")
	String area;
	String city;
	String pincode;
	String state;
	LocalDateTime createdDate;
	LocalDateTime updatedDate;

}

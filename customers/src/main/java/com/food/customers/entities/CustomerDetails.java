package com.food.customers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String username;
	
	@NotBlank(message="Name cannot be blank")
	private String name;
	
	@NotNull(message="Phone Number cannot be blank")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "phoneNumeber is not valid")
	private String phoneNumeber;
	
	@NotBlank(message="Email cannot be blank")
	private String emailId;

}

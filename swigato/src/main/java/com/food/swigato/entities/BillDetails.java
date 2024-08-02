package com.food.swigato.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "billDetails")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Double itemTotal;
	private Double gstAndResturantCharges;
	private Double deliveryCharge;
	private Double platformFee;
	private Double extraCharges;
	private Double grandTotal;
	private String cartId;

}

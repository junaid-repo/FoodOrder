package com.food.swigato.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "CartSummary")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer foodId;
	private Integer addressId;
	private Integer customerCode;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bill_id", referencedColumnName = "id")
	private BillDetails billDetails;
}

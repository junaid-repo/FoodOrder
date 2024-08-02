package com.food.swigato.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.swigato.dto.CartSummary;

public interface CartSummaryRepository extends JpaRepository<CartSummary, String>{

}

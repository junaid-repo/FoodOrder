package com.food.swigato.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.food.swigato.dto.FoodDetails;

public interface FoodDetailsRepository extends JpaRepository<FoodDetails, Integer>{

	@Query(value="select * from food_details fd where fd.cart_id=?1", nativeQuery=true)
	List<FoodDetails> findByCartId(String id);

}

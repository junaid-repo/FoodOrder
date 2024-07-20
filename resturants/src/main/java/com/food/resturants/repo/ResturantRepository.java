package com.food.resturants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.food.resturants.entities.ResturantsDetails;

import jakarta.transaction.Transactional;

public interface ResturantRepository extends JpaRepository<ResturantsDetails, Integer>{

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value="update resturants_details set resturant_code=?2 where id=?1", nativeQuery=true)
	void updateResturantCode(Integer id, String resName);

	@Query(value="select * from resturants_details rd where rd.resturant_code=?1", nativeQuery=true)
	ResturantsDetails findByResturantCode(String resturantCode);

}

package com.food.customers.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.food.customers.entities.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer>{

	@Query(value="select * from customer_details cd where cd.username=?1", nativeQuery=true)
	CustomerDetails findByCustomerCode(String userCode);

}

package com.food.customers.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.food.customers.entities.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer>{

	@Query(value="select * from customer_address where customer_code=?1", nativeQuery=true)
	List<CustomerAddress> getAddressList(String customerCode);

}

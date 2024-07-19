package com.food.customers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.customers.entities.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer>{

}

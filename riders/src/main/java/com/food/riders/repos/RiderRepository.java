package com.food.riders.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.riders.entity.RiderInfo;

@Repository
public interface RiderRepository extends JpaRepository<RiderInfo, Integer>{

}

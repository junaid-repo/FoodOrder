package com.food.resturants.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.resturants.entities.ResturantMenu;

public interface ResturantMenuRepository extends JpaRepository<ResturantMenu, Integer>{

}

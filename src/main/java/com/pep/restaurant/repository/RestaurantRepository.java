package com.pep.restaurant.repository;

import com.pep.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
    /**
     * Get Restaurant by name
     * @param name restaurant name
     * @return restaurant as optional
     */
    Optional<Restaurant> findRestaurantByName(String name);

}

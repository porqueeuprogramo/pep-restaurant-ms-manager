package com.pep.restaurant.service.mapper;

import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.service.model.RestaurantDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);

    List<Restaurant> mapRestaurantDTOListToRestaurantList(List<RestaurantDTO> restaurantDTOList);

    List<RestaurantDTO> mapRestaurantListToRestaurantDTOList(List<Restaurant> restaurantList);

}
package com.pep.restaurant.service;

import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.logging.Logger;
import com.pep.restaurant.logging.enumeration.LogTag;
import com.pep.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantService {

    private static final Logger logger = new Logger(RestaurantService.class);
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Create Restaurant.
     * @param restaurant restaurant.
     * @return restaurant created.
     */
    public Restaurant createRestaurant(final Restaurant restaurant){
        final Optional<Restaurant> restaurantOptional = restaurantRepository.findRestaurantByName(restaurant.getName());
        if(restaurantOptional.isEmpty()){
            return restaurantRepository.save(restaurant);
        }
        throw new NullPointerException("Restaurant already exists!!!");
    }

    /**
     * Get Restaurant.
     * @param restaurantId restaurant id.
     * @return restaurant retrieved.
     */
    public Restaurant getRestaurant(final long restaurantId){
        final Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if(restaurantOptional.isEmpty()){
            throw new NullPointerException("Restaurant to get not exists!!!");
        }
        return restaurantOptional.get();
    }

    /**
     * Edit Restaurant.
     * @param restaurantId restaurant id.
     * @param restaurantNew restaurant new.
     * @return restaurant edited.
     */
    public Restaurant editRestaurant(final long restaurantId,  final Restaurant restaurantNew){
        final Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if(restaurantOptional.isEmpty()){
            throw new NullPointerException("Restaurant to be edited not exists!!!");
        }
        //edit restaurant
        restaurantOptional.get()
                .name(restaurantNew.getName())
                .location(restaurantNew.getLocation())
                .capacity(restaurantNew.getCapacity());

        return restaurantRepository.save(restaurantOptional.get());
    }

    /**
     * Delete Restaurant.
     * @param restaurantId restaurant id.
     * @return restaurant deleted.
     */
    public Restaurant deleteRestaurant(final long restaurantId){
        final Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if(restaurantOptional.isEmpty()){
            throw new NullPointerException("Restaurant to be deleted not exists!!!");
        }
        restaurantRepository.deleteById(restaurantId);
        return restaurantOptional.get();
    }

    /**
     * Get All Restaurants.
     * @return List of restaurants.
     */
    public List<Restaurant> getAllRestaurants(){
        final List<Restaurant> restaurantList = restaurantRepository.findAll();
        if(restaurantList.isEmpty()){
            throw new NullPointerException("No Restaurants persisted!!!");
        }
        logger.info(Arrays.asList(LogTag.RESTAURANTS, LogTag.RETRIEVED), "Get All Restaurants from db");
        return restaurantList;
    }
}

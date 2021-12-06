package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.logging.enumeration.LogTag;
import com.pep.restaurant.ms.manager.logging.Logger;
import com.pep.restaurant.ms.manager.repository.RestaurantRepository;
import com.pep.restaurant.ms.manager.repository.EmployeeRepository;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private static final Logger LOGGER = new Logger(RestaurantService.class);
    private final RestaurantRepository restaurantRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public RestaurantService(final RestaurantRepository restaurantRepository,
                             final EmployeeRepository employeeRepository) {
        this.restaurantRepository = restaurantRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Create Restaurant.
     * @param restaurant restaurant.
     * @return restaurant created.
     */
    public Restaurant createRestaurant(final Restaurant restaurant){
        final Optional<Restaurant> restaurantOptional = restaurantRepository.findRestaurantByName(restaurant.getName());
        if(restaurantOptional.isEmpty()){

            LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.RESTAURANTS, LogTag.PERSISTED),
                    "Create Restaurant: " + restaurant.toString());

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
        final Optional<Restaurant> restaurantOptional = getRestaurantById(restaurantId,
                "Restaurant does not exists!!!");

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.RESTAURANTS, LogTag.RETRIEVED),
                "Get Restaurant by id: " + restaurantId);

        return restaurantOptional.get();
    }

    /**
     * Edit Restaurant.
     * @param restaurantId restaurant id.
     * @param restaurantNew restaurant new.
     * @return restaurant edited.
     */
    public Restaurant editRestaurant(final long restaurantId,  final Restaurant restaurantNew){
        final Optional<Restaurant> restaurantOptional = getRestaurantById(restaurantId,
                "Restaurant to be edited not exists!!!");
        //edit restaurant
        restaurantOptional.get()
                .name(restaurantNew.getName())
                .location(restaurantNew.getLocation())
                .capacity(restaurantNew.getCapacity())
                .menu(restaurantNew.getMenu())
                .setEmployeeList(restaurantNew.getEmployeeList());

        LOGGER.info(MDC.get("correlationId"),  Arrays.asList(LogTag.RESTAURANTS, LogTag.EDITED),
                "Edit Restaurant by id " + restaurantId);

        return restaurantRepository.save(restaurantOptional.get());
    }

    /**
     * Delete Restaurant.
     * @param restaurantId restaurant id.
     * @return restaurant deleted.
     */
    public Restaurant deleteRestaurant(final long restaurantId){
        final Optional<Restaurant> restaurantOptional = getRestaurantById(restaurantId,
                "Restaurant to be deleted not exists!!!");
        restaurantRepository.deleteById(restaurantId);

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.RESTAURANTS, LogTag.DELETED),
                "Delete Restaurant by id: " + restaurantId);

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
        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.RESTAURANTS, LogTag.RETRIEVED),
                "Get All Restaurants from db");
        return restaurantList;
    }

    /**
     * Add employee from restaurant.
     * @param restaurantId restaurant id.
     * @param employeeId employee id.
     * @return Restaurant.
     */
    public Restaurant addEmployee(final long restaurantId, final long employeeId) {
        final Optional<Restaurant> restaurantOptional = getRestaurantById(restaurantId,
                "Restaurant to add employee does not exists!!!");

        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isEmpty()){
            throw new NullPointerException("Employee does not exists!!!");
        }

        restaurantOptional.get()
                .addEmployee(employeeOptional.get());

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.RESTAURANTS, LogTag.EDITED),
                "Add Employee with id: " + employeeId + " to Restaurant with id: " + restaurantId);

        return restaurantRepository.save(restaurantOptional.get());
    }

    /**
     * Remove employee from restaurant.
     * @param restaurantId restaurant id.
     * @param employeeId employee id.
     * @return Restaurant.
     */
    public Restaurant removeEmployee(final long restaurantId, final long employeeId) {
        final Optional<Restaurant> restaurantOptional = getRestaurantById(restaurantId,
                "Restaurant to remove employee does not exists!!!");

        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isEmpty()){
            throw new NullPointerException("Employee does not exists!!!");
        }

        restaurantOptional.get()
                .removeEmployee(employeeOptional.get());

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.RESTAURANTS, LogTag.EDITED),
                "Remove Employee with id: " + employeeId + " from Restaurant with id: " + restaurantId);

        return restaurantRepository.save(restaurantOptional.get());
    }

    /**
     * Find Restaurant on Repository.
     * @param restaurantId restaurant Id.
     * @param exceptionMessage exception Message.
     * @return Optional of Restaurant.
     */
    private Optional<Restaurant> getRestaurantById(final long restaurantId, final String exceptionMessage) {
        final Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isEmpty()) {
            throw new NullPointerException(exceptionMessage);
        }
        return restaurantOptional;
    }
}

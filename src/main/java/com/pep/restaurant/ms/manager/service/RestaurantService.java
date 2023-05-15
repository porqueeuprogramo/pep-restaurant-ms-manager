package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.domain.Address;
import com.pep.restaurant.ms.manager.domain.Location;
import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.domain.Employee;
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
import java.util.UUID;

@Service
public class RestaurantService {

    private static final Logger LOGGER = new Logger(RestaurantService.class);
    private final RestaurantRepository restaurantRepository;

    private final LocationService locationService;

    private final AddressService addressService;

    private final MenuService menuService;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public RestaurantService(final RestaurantRepository restaurantRepository,
                             final LocationService locationService,
                             final AddressService addressService,
                             final MenuService menuService,
                             final EmployeeRepository employeeRepository) {
        this.restaurantRepository = restaurantRepository;
        this.locationService = locationService;
        this.addressService = addressService;
        this.menuService = menuService;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Create Restaurant.
     * @param restaurant restaurant.
     * @return restaurant created.
     */
    public Restaurant createRestaurant(final Restaurant restaurant){
        final Optional<Restaurant> restaurantOptional = restaurantRepository.findByUid(restaurant.getUid());
        if(restaurantOptional.isEmpty()){

            LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.RESTAURANTS, LogTag.PERSISTED),
                    "Create Restaurant: " + restaurant.toString());

            restaurant.uid(UUID.randomUUID().toString());
            final Restaurant restaurantBuildEnriched = buildRestaurantInfo(restaurant);
            return restaurantRepository.save(restaurant);
        }
        throw new NullPointerException("Restaurant already exists!!!");
    }

    private Restaurant buildRestaurantInfo(final Restaurant restaurant) {
        final Menu menu = menuService.createMenu(restaurant.getMenu());
        final Address addressPersisted = addressService.createAddress(restaurant.getLocation().getAddress());
        final Location locationPersisted = locationService.createLocation(new Location()
                .address(addressPersisted)
                .locationCoordinate(
                        restaurant
                                .getLocation()
                                .getCoordinate()));
        return restaurant
                .menu(menu)
                .location(locationPersisted);
    }

    /**
     * Get Restaurant.
     * @param restaurantId restaurant id.
     * @return restaurant retrieved.
     */
    public Restaurant getRestaurant(final String restaurantId){
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
    public Restaurant editRestaurant(final String restaurantId,  final Restaurant restaurantNew){
        final Optional<Restaurant> restaurantOptional = getRestaurantById(restaurantId,
                "Restaurant to be edited not exists!!!");

        LOGGER.info(MDC.get("correlationId"),  Arrays.asList(LogTag.RESTAURANTS, LogTag.EDITED),
                "Edit Restaurant by id " + restaurantId);

        final Address editedAddress = new Address()
                .id(restaurantOptional.get().getLocation().getAddress().getId())
                .name(restaurantNew.getLocation().getAddress().getName())
                .postalCode(restaurantNew.getLocation().getAddress().getPostalCode())
                .city(restaurantNew.getLocation().getAddress().getCity())
                .country(restaurantNew.getLocation().getAddress().getCountry());

        final Location editedLocation = new Location()
                .id(restaurantOptional.get().getLocation().getId())
                .locationCoordinate(restaurantOptional.get().getLocation().getCoordinate())
                .address(editedAddress);

        final Menu editedMenu = new Menu()
                .id(restaurantOptional.get().getMenu().getId())
                .language(restaurantNew.getMenu().getLanguage());

        final Address addressEdited = addressService.editAddress(
                restaurantOptional.get()
                        .getLocation().getAddress().getId(), editedAddress);

        final Location locationEdited = locationService.editLocation(
                restaurantOptional.get().getLocation().getId(), editedLocation);

        final Menu menuEdited = menuService.editMenu(
                restaurantOptional.get().getMenu().getId(), editedMenu);

        //edit restaurant
        restaurantOptional.get()
                .name(restaurantNew.getName())
                .location(locationEdited.address(addressEdited))
                .capacity(restaurantNew.getCapacity())
                .menu(menuEdited)
                .hereId(restaurantNew.getHereId())
                .schedule(restaurantNew.getScheduleRoutine())
                .employeeList(restaurantNew.getEmployeeList());

        return restaurantRepository.save(restaurantOptional.get());
    }

    /**
     * Delete Restaurant.
     * @param restaurantId restaurant id.
     * @return restaurant deleted.
     */
    public Restaurant deleteRestaurant(final String restaurantId){
        final Optional<Restaurant> restaurantOptional = getRestaurantById(restaurantId,
                "Restaurant to be deleted not exists!!!");

        restaurantRepository.delete(restaurantOptional.get());

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
    public Restaurant addEmployee(final String restaurantId, final String employeeId) {
        final Optional<Restaurant> restaurantOptional = getRestaurantById(restaurantId,
                "Restaurant to add employee does not exists!!!");

        final Optional<Employee> employeeOptional = employeeRepository.findByUid(employeeId);
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
    public Restaurant removeEmployee(final String restaurantId, final String employeeId) {
        final Optional<Restaurant> restaurantOptional = getRestaurantById(restaurantId,
                "Restaurant to remove employee does not exists!!!");

        final Optional<Employee> employeeOptional = employeeRepository.findByUid(employeeId);
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
    private Optional<Restaurant> getRestaurantById(final String restaurantId, final String exceptionMessage) {
        final Optional<Restaurant> restaurantOptional = restaurantRepository.findByUid(restaurantId);
        if (restaurantOptional.isEmpty()) {
            throw new NullPointerException(exceptionMessage);
        }
        return restaurantOptional;
    }
}

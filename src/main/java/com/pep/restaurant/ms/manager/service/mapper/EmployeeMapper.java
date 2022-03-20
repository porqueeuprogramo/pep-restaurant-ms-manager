package com.pep.restaurant.ms.manager.service.mapper;

import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.domain.ScheduleRoutine;
import com.pep.restaurant.ms.manager.domain.ScheduleTime;
import com.pep.restaurant.ms.manager.domain.Location;
import com.pep.restaurant.ms.manager.domain.Address;
import com.pep.restaurant.ms.manager.domain.Coordinate;
import com.pep.restaurant.ms.manager.service.model.MenuDTO;
import com.pep.restaurant.ms.manager.service.model.EmployeeDTO;
import com.pep.restaurant.ms.manager.service.model.RestaurantDTO;
import com.pep.restaurant.ms.manager.service.model.ScheduleRoutineDTO;
import com.pep.restaurant.ms.manager.service.model.ScheduleTimeDTO;
import com.pep.restaurant.ms.manager.service.model.LocationDTO;
import com.pep.restaurant.ms.manager.service.model.AddressDTO;
import com.pep.restaurant.ms.manager.service.model.CoordinateDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    /**
     * Map Employee List to Employee DTO List.
     * @param employeeList employee List.
     * @return Employee List DTO.
     */
    public List<EmployeeDTO> mapEmployeeListToEmployeeDTOList(final List<Employee> employeeList) {
        return employeeList != null
                ? employeeList
                .stream()
                .map(this::mapEmployeeToEmployeeDTO)
                .collect(Collectors.toList())
                : null;
    }

    /**
     * Map employee to Employee DTO.
     * @param employee employee.
     * @return Employee DTO.
     */
    public EmployeeDTO mapEmployeeToEmployeeDTO(final Employee employee) {
        return employee != null ?
                new EmployeeDTO()
                        .id(employee.getId())
                        .role(employee.getRole())
                        .schedule(mapScheduleRoutineToScheduleRoutineDTO(employee.getScheduleRoutine()))
                        .restaurantList(mapRestaurantListToRestaurantDTOList(employee.getRestaurantList()))
                : null;

    }

    /**
     * Map Employee DTO to Employee.
     * @param employeeDTO employee DTO.
     * @return employee.
     */
    public Employee mapEmployeeDTOToEmployee(final EmployeeDTO employeeDTO) {
        return employeeDTO != null ?
                new Employee()
                        .id(employeeDTO.getId())
                        .role(employeeDTO.getRole())
                        .schedule(mapScheduleRoutineDTOToScheduleRoutine(employeeDTO.getSchedule()))
                        .restaurantList(mapRestaurantDTOListToRestaurantList(employeeDTO.getRestaurantList()))
                : null;
    }

    /**
     * Map Schedule Routine to Schedule Routine DTO.
     * @param scheduleRoutine schedule routine.
     * @return Schedule Routine DTO.
     */
    public ScheduleRoutineDTO mapScheduleRoutineToScheduleRoutineDTO(final ScheduleRoutine scheduleRoutine) {
        final Map<String, List<ScheduleTimeDTO>> scheduleRoutineDTOMap = new HashMap<>();
        if(scheduleRoutine != null){
            scheduleRoutine
                    .getScheduleRoutine()
                    .forEach((dayOfWeek, scheduleTimeMap) -> mapScheduleTimeMapToScheduleTimeDTOMap(
                            dayOfWeek,
                            scheduleTimeMap,
                            scheduleRoutineDTOMap));
            return new ScheduleRoutineDTO().daysScheduleMap(scheduleRoutineDTOMap);
        }
        return null;
    }

    /**
     * Map Schedule Routine DTO to Schedule Routine.
     * @param scheduleRoutineDTO schedule routine DTO.
     * @return Schedule Routine.
     */
    public ScheduleRoutine mapScheduleRoutineDTOToScheduleRoutine(final ScheduleRoutineDTO scheduleRoutineDTO) {
        final Map<DayOfWeek, List<ScheduleTime>> scheduleRoutineDTOMap = new HashMap<>();

        if(scheduleRoutineDTO != null) {
            scheduleRoutineDTO
                    .getScheduleRoutine()
                    .forEach((dayOfWeek, scheduleTimeMap) -> mapScheduleTimeDTOListToScheduleTimeMap(
                            dayOfWeek,
                            scheduleTimeMap,
                            scheduleRoutineDTOMap));
            return new ScheduleRoutine().daysScheduleMap(scheduleRoutineDTOMap);
        }
        return null;
    }

    private Set<RestaurantDTO> mapRestaurantListToRestaurantDTOList(final Set<Restaurant> restaurantList) {
        return restaurantList != null
                ? restaurantList
                .stream()
                .map(this::mapRestaurantToRestaurantDTO)
                .collect(Collectors.toSet())
                : null;
    }

    private RestaurantDTO mapRestaurantToRestaurantDTO(final Restaurant restaurant) {
        return restaurant != null ?
                new RestaurantDTO()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .capacity(restaurant.getCapacity())
                        .menu(mapMenuToMenuDTO(restaurant.getMenu()))
                        .hereId(restaurant.getHereId())
                        .location(mapLocationToLocationDTO(restaurant.getLocation()))
                        .schedule(mapScheduleRoutineToScheduleRoutineDTO(restaurant.getScheduleRoutine()))
                        .employeeList(null)
                : null;

    }

    private Set<Restaurant> mapRestaurantDTOListToRestaurantList(final Set<RestaurantDTO> restaurantDTOList) {
        return restaurantDTOList != null
                ? restaurantDTOList
                .stream()
                .map(this::mapRestaurantDTOToRestaurant)
                .collect(Collectors.toSet())
                : null;
    }

    private Restaurant mapRestaurantDTOToRestaurant(final RestaurantDTO restaurantDTO) {
        return restaurantDTO != null ?
                new Restaurant()
                        .id(restaurantDTO.getId())
                        .name(restaurantDTO.getName())
                        .capacity(restaurantDTO.getCapacity())
                        .menu(mapMenuDTOToMenu(restaurantDTO.getMenu()))
                        .hereId(restaurantDTO.getHereId())
                        .location(mapLocationDTOToLocation(restaurantDTO.getLocation()))
                        .schedule(mapScheduleRoutineDTOToScheduleRoutine(restaurantDTO.getSchedule()))
                        .employeeList(null)
                : null;

    }

    private MenuDTO mapMenuToMenuDTO(final Menu menu) {
        return menu != null ?
                new MenuDTO()
                        .id(menu.getId())
                        .language(menu.getLanguage())
                : null;
    }

    private Menu mapMenuDTOToMenu(final MenuDTO menuDTO) {
        return menuDTO != null ?
                new Menu()
                        .id(menuDTO.getId())
                        .language(menuDTO.getLanguage())
                : null;
    }

    private void mapScheduleTimeMapToScheduleTimeDTOMap(final DayOfWeek dayOfWeek,
                                                        final List<ScheduleTime> scheduleTimeList,
                                                        final Map<String, List<ScheduleTimeDTO>>
                                                                scheduleRoutineDTOMap) {
        final List<ScheduleTimeDTO> scheduleTimeDTOList = scheduleTimeList
                .stream()
                .map(this::mapScheduleTimeToScheduleTimeDTO)
                .collect(Collectors.toList());

        scheduleRoutineDTOMap.put(dayOfWeek.name(), scheduleTimeDTOList);

    }

    private ScheduleTimeDTO mapScheduleTimeToScheduleTimeDTO(final ScheduleTime scheduleTime) {
        return scheduleTime != null ?
                new ScheduleTimeDTO()
                        .startTime(scheduleTime.getStartTime())
                        .endTime(scheduleTime.getEndTime())
                : null;
    }

    private void mapScheduleTimeDTOListToScheduleTimeMap(final String dayOfWeek,
                                                         final List<ScheduleTimeDTO> scheduleTimeDTOList,
                                                         final Map<DayOfWeek, List<ScheduleTime>>
                                                                 scheduleRoutineMap) {
        final List<ScheduleTime> scheduleTimeList = scheduleTimeDTOList
                .stream()
                .map(this::mapScheduleTimeDTOToScheduleTime)
                .collect(Collectors.toList());

        scheduleRoutineMap.put(DayOfWeek.valueOf(dayOfWeek), scheduleTimeList);

    }

    private ScheduleTime mapScheduleTimeDTOToScheduleTime(final ScheduleTimeDTO scheduleTimeDTO) {
        return scheduleTimeDTO != null ?
                new ScheduleTime()
                        .startTime(scheduleTimeDTO.getStartTime())
                        .endTime(scheduleTimeDTO.getEndTime())
                : null;
    }

    private LocationDTO mapLocationToLocationDTO(final Location location) {
        return location != null ?
                new LocationDTO()
                        .id(location.getId())
                        .address(mapAddressToAddressDTO(location.getAddress()))
                        .locationCoordinate(mapCoordinateToCoordinateDTO(location.getCoordinate()))
                : null;
    }

    private Location mapLocationDTOToLocation(final LocationDTO locationDTO) {
        return locationDTO != null ?
                new Location()
                        .id(locationDTO.getId())
                        .address(mapAddressDTOToAddress(locationDTO.getAddress()))
                        .locationCoordinate(mapCoordinateDTOToCoordinate(locationDTO.getLocationCoordinate()))
                : null;
    }

    private AddressDTO mapAddressToAddressDTO(final Address address) {
        return address != null ?
                new AddressDTO()
                        .id(address.getId())
                        .name(address.getName())
                        .postalCode(address.getPostalCode())
                        .city(address.getCity())
                        .country(address.getCountry())
                : null;
    }

    private Address mapAddressDTOToAddress(final AddressDTO addressDTO) {
        return addressDTO != null ?
                new Address()
                        .id(addressDTO.getId())
                        .name(addressDTO.getName())
                        .postalCode(addressDTO.getPostalCode())
                        .city(addressDTO.getCity())
                        .country(addressDTO.getCountry())
                : null;
    }

    private CoordinateDTO mapCoordinateToCoordinateDTO(final Coordinate coordinate) {
        return coordinate != null ?
                new CoordinateDTO()
                        .latitude(coordinate.getLatitude())
                        .longitude(coordinate.getLongitude())
                : null;
    }

    private Coordinate mapCoordinateDTOToCoordinate(final CoordinateDTO coordinateDTO) {
        return coordinateDTO != null ?
                new Coordinate()
                        .latitude(coordinateDTO.getLatitude())
                        .longitude(coordinateDTO.getLongitude())
                : null;
    }
}

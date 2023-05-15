package com.pep.restaurant.ms.manager;

import com.pep.restaurant.ms.manager.domain.*;
import com.pep.restaurant.ms.manager.service.model.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class ApplicationDataProvider {

    public Employee getEmployeeToCreate(){
        return new Employee()
                .role("CHEF");
    }

    public Employee getEmployeeWithId(){
        return new Employee()
                .id(1L)
                .uid("1L")
                .role("CHEF");
    }

    public Restaurant getRestaurant(){
        return new Restaurant()
                .uid("1L")
                .name("Francesinhas")
                .capacity(100)
                .menu(getMenu())
                .hereId("hereId")
                .location(getLocation())
                .schedule(getScheduleRoutine());
    }

    public Restaurant getRestaurantToCreate(){
        return new Restaurant()
                .name("Francesinhas")
                .capacity(100)
                .menu(getMenu())
                .hereId("hereId")
                .location(getLocation())
                .schedule(getScheduleRoutine());
    }

    public Restaurant getRestaurant2(){
        return new Restaurant()
                .name("Francesinhas 2")
                .capacity(300)
                .menu(getMenu2())
                .hereId("hereId")
                .location(getLocation2())
                .schedule(getScheduleRoutine2());
    }

    public Restaurant getRestaurantWithIds(){
        return new Restaurant()
                .uid("1L")
                .name("Francesinhas")
                .capacity(100)
                .menu(getMenu())
                .hereId("hereId")
                .location(getLocation())
                .schedule(getScheduleRoutine());
    }

    public ScheduleRoutine getScheduleRoutine2(){

        ScheduleTime scheduleTime = new ScheduleTime()
                .startTime(LocalTime.of(15, 30))
                .endTime(LocalTime.of(18, 30));

        Map<DayOfWeek, List<ScheduleTime>> dayMap = new HashMap<>();
        dayMap.put(DayOfWeek.MONDAY, List.of(scheduleTime));

        return new ScheduleRoutine()
                .daysScheduleMap(dayMap);
    }


    public ScheduleRoutine getScheduleRoutine(){

        ScheduleTime scheduleTime = new ScheduleTime()
                .startTime(LocalTime.of(12, 30))
                .endTime(LocalTime.of(20, 30));

        Map<DayOfWeek, List<ScheduleTime>> dayMap = new HashMap<>();
        dayMap.put(DayOfWeek.MONDAY, List.of(scheduleTime));

        return new ScheduleRoutine()
                .daysScheduleMap(dayMap);
    }

    public ScheduleRoutineDTO getScheduleRoutineDTO(){

        ScheduleTimeDTO scheduleTimeDTO = new ScheduleTimeDTO()
                .startTime(LocalTime.of(12, 30))
                .endTime(LocalTime.of(20, 30));

        Map<String, List<ScheduleTimeDTO>> dayMap = new HashMap<>();
        dayMap.put("MONDAY", List.of(scheduleTimeDTO));

        return new ScheduleRoutineDTO()
                .daysScheduleMap(dayMap);
    }

    public Coordinate getCoordinate(){
        return new Coordinate()
                        .latitude(12.0000)
                        .longitude(13.0000);
    }

    public Coordinate getCoordinate2(){
        return new Coordinate()
                .latitude(14.0000)
                .longitude(15.0000);
    }

    public Address getAddressWithId(){
        return new Address()
                .id(1L)
                .name("Rua Teste")
                .city("Porto")
                .country("Portugal")
                .postalCode("9999-999");
    }

    public Address getAddress(){
        return new Address()
                .name("Rua Teste")
                .city("Porto")
                .country("Portugal")
                .postalCode("9999-999");
    }

    public Address getAddress2(){
        return new Address()
                .name("Rua Teste 2")
                .city("Porto 2")
                .country("Portugal 2")
                .postalCode("1111-999");
    }

    public Location getLocationWithId(){
        return new Location()
                .id(1L)
                .locationCoordinate(getCoordinate())
                .address(getAddress());
    }

    public Location getLocation(){
        return new Location()
                        .locationCoordinate(getCoordinate())
                        .address(getAddress());
    }

    public Location getLocation2(){
        return new Location()
                .locationCoordinate(getCoordinate2())
                .address(getAddress2());
    }

    public Menu getMenu(){
        return new Menu()
                .language("PORTUGUESE");
    }

    public Menu getMenuWithId(){
        return new Menu()
                .id(1L)
                .language("PORTUGUESE");
    }

    public Menu getMenu2(){
        return new Menu()
                .id(1L)
                .language("ENGLISH");
    }

    public Employee getEmployee(){
        Employee employee = new Employee()
                .uid("1L")
                .role("CHEF")
                .schedule(getScheduleRoutine());
        employee.setRestaurantList(Set.of(getRestaurant()));
        return employee;
    }

    public LocationDTO getLocationDTO(){
        return new LocationDTO()
                .locationCoordinate(getCoordinateDTO())
                .address(getAddressDTO());
    }

    public AddressDTO getAddressDTO(){
        return new AddressDTO()
                .name("Rua Teste")
                .city("Porto")
                .country("Portugal")
                .postalCode("9999-999");
    }

    public CoordinateDTO getCoordinateDTO(){
        return new CoordinateDTO()
                .latitude(12.0000)
                .longitude(13.0000);
    }

    public RestaurantDTO getRestaurantDTO(){
        return new RestaurantDTO()
                .name("Francesinhas")
                .capacity(100)
                .menu(getMenuDTO())
                .hereId("hereId")
                .location(getLocationDTO())
                .schedule(getScheduleRoutineDTO());
    }

    public MenuDTO getMenuDTO(){
        return new MenuDTO()
                .language("PORTUGUESE");
    }

    public EmployeeDTO getEmployeeDTO(){
        EmployeeDTO employee = new EmployeeDTO()
                .role("CHEF");
        employee.setRestaurantList(Set.of(getRestaurantDTO()));
        return employee;
    }

    public Restaurant getRestaurantWithEmployeeList(){
        return new Restaurant()
                .name("Francesinhas")
                .capacity(100)
                .menu(getMenu())
                .hereId("hereId")
                .location(getLocation())
                .schedule(getScheduleRoutine())
                .employeeList(Set.of(getEmployeeWithoutRestaurantList()));
    }

    public Employee getEmployeeWithoutRestaurantListAndWithoutSchedule(){
        return new Employee()
                .uid("1L")
                .role("CHEF");
    }

    public Employee getEmployeeWithoutRestaurantList(){
        return new Employee()
                .role("CHEF");
    }

    public Employee getEmployeeWithoutRestaurantListWithId(){
        return new Employee()
                .id(1L)
                .role("CHEF");
    }

    public RestaurantDTO getRestaurantDTOWithEmployeeListDTO(){
        return new RestaurantDTO()
                .name("Francesinhas")
                .capacity(100)
                .menu(getMenuDTO())
                .hereId("hereId")
                .location(getLocationDTO())
                .schedule(getScheduleRoutineDTO())
                .employeeList(Set.of(getEmployeeDTOWithoutRestaurantListDTO()));
    }

    public EmployeeDTO getEmployeeDTOWithoutRestaurantListDTO(){
        return new EmployeeDTO()
                .role("CHEF");
    }

}

package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Restaurant Dto class
 */
public class RestaurantDTO implements Serializable {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("hereId")
    private String hereId;

    @JsonProperty("location")
    private LocationDTO location;

    @JsonProperty("capacity")
    private int capacity;

    @JsonProperty("menu")
    private MenuDTO menu;

    @JsonProperty("schedule")
    private ScheduleRoutineDTO schedule;

    @JsonProperty("employeeList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<EmployeeDTO> employeeList = new HashSet<>();

    /**
     * Method to get a RestauranDTO id.
     * @return id.
     */
    public long getId() {
        return id;
    }

    /**
     * Method to set a RestaurantDTO id.
     * @param id to be set.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Builder RestaurantDTO for id.
     * @param id id to build.
     * @return restaurantDTO with id.
     */
    public RestaurantDTO id(final long id){
        this.id = id;
        return this;
    }

    /**
     * Get Restaurant here id.
     * @return restaurant here id.
     */
    public String getHereId() {
        return hereId;
    }

    /**
     * Set Restaurant here id.
     * @param hereId restaurant id.
     */
    public void setHereId(final String hereId) {
        this.hereId = hereId;
    }

    /**
     * Builder Restaurant for here id.
     * @param hereId here id to build.
     * @return restaurant with id.
     */
    public RestaurantDTO hereId(final String hereId){
        this.hereId = hereId;
        return this;
    }

    /**
     * Method to get a RestaurantDTO name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set a RestaurantDTO name.
     * @param name name to be set.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Builder RestaurantDTO for name.
     * @param name name to build.
     * @return restaurantDTO with name.
     */
    public RestaurantDTO name(final String name){
        this.name = name;
        return this;
    }

    /**
     * Method to get a RestaurantDTO Location.
     * @return location.
     */
    public LocationDTO getLocation() {
        return location;
    }

    /**
     * Method to set a RestaurantDTO location.
     * @param location location to be set.
     */
    public void setLocation(final LocationDTO location) {
        this.location = location;
    }

    /**
     * Builder RestaurantDTO for location
     * @param location location to build.
     * @return restaurantDTO with location.
     */
    public RestaurantDTO location(final LocationDTO location){
        this.location = location;
        return this;
    }

    /**
     * Method to get a RestaurantDTO Schedule.
     * @return schedule.
     */
    public ScheduleRoutineDTO getSchedule() {
        return schedule;
    }

    /**
     * Method to set a RestaurantDTO schedule.
     * @param schedule schedule to be set.
     */
    public void setSchedule(final ScheduleRoutineDTO schedule) {
        this.schedule = schedule;
    }

    /**
     * Builder RestaurantDTO for schedule
     * @param schedule schedule to build.
     * @return restaurantDTO with schedule.
     */
    public RestaurantDTO schedule(final ScheduleRoutineDTO schedule){
        this.schedule = schedule;
        return this;
    }

    /**
     * Method to get RestaurantDTO capacity.
     * @return capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Method to set RestaurantDTO capacity.
     * @param capacity to be set.
     */
    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    /**
     * Builder RestaurantDTO for capacity.
     * @param capacity capacity to build.
     * @return restaurantDTO with capacity.
     */
    public RestaurantDTO capacity(final int capacity){
        this.capacity = capacity;
        return this;
    }

    /**
     * Method to get RestaurantDTO menu.
     * @return menu.
     */
    public MenuDTO getMenu() {
        return menu;
    }

    /**
     * Method to set RestaurantDTO menu.
     * @param menu to be set.
     */
    public void setMenu(final MenuDTO menu) {
        this.menu = menu;
    }

    /**
     * Builder RestaurantDTO for menu.
     * @param menu menu to build.
     * @return restaurantDTO with menu.
     */
    public RestaurantDTO menu(final MenuDTO menu){
        this.menu = menu;
        return this;
    }

    /**
     * Method to get Restaurant employeeDTO list.
     * @return employeeDTO list.
     */
    public Set<EmployeeDTO> getEmployeeList() {
        return employeeList;
    }

    /**
     * Method to set Restaurant employeeDTO List
     * @param employeeList employeeDTO list.
     */
    public void setEmployeeList(final Set<EmployeeDTO> employeeList){
        this.employeeList = employeeList;
    }

    /**
     * Builder RestaurantDTO for employeeList.
     * @param employeeList employeeList to build.
     * @return restaurantDTO with employeeList.
     */
    public RestaurantDTO employeeList(final Set<EmployeeDTO> employeeList){
        this.employeeList = employeeList;
        return this;
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hereId='" + hereId + '\'' +
                ", location=" + location +
                ", capacity=" + capacity +
                ", menu=" + menu +
                ", schedule=" + schedule +
                ", employeeList=" + employeeList +
                '}';
    }
}

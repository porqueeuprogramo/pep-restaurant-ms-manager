package com.pep.restaurant.service.model;

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

    @JsonProperty("location")
    private String location;

    @JsonProperty("capacity")
    private int capacity;

    @JsonProperty("menu")
    private MenuDTO menu;

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
     * Method to get a RestaurantDTO location.
     * @return location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Method to set a RestaurantDTO location.
     * @param location to set.
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * Builder RestaurantDTO for location.
     * @param location location to build.
     * @return restaurantDTO with location.
     */
    public RestaurantDTO location(final String location){
        this.location = location;
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
}

package com.pep.restaurant.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

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
}

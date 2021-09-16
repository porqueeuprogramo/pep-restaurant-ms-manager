package com.pep.restaurant.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.domain.Schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee Dto class
 */
public class EmployeeDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("role")
    private String role;

    @JsonProperty("restaurant")
    private Restaurant restaurant;

    @JsonProperty("schedule")
    private List<Schedule> scheduleList = new ArrayList<>();

    /**
     * Get id employee.
     * @return employee id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set id employee.
     * @param id employee id.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Get employee role.
     * @return employee role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Set employee role.
     * @param role employee role.
     */
    public void setRole(final String role) {
        this.role = role;
    }



    /**
     * Builder Employee for role.
     * @param role role to build.
     * @return employee with role.
     */
    public EmployeeDTO role(final String role){
        this.role = role;
        return this;
    }

    /**
     * Get employee restaurant.
     * @return employee restaurant.
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Set employee restaurant.
     * @param restaurant employee restaurant.
     */
    public void setRestaurant(final Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * Builder Employee for restaurant.
     * @param restaurant restaurant to build.
     * @return employee with restaurant.
     */
    public EmployeeDTO restaurant(final Restaurant restaurant){
        this.restaurant = restaurant;
        return this;
    }

    /**
     * Get employee schedule.
     * @return employee schedule.
     */
    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    /**
     * Set employee schedule.
     * @param scheduleList employee schedule list.
     */
    public void setScheduleList(final List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}

package com.pep.restaurant.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("restaurantList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RestaurantDTO> restaurantList = new ArrayList<>();

    @JsonProperty("schedule")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ScheduleDTO schedule;

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
     * Get employee restaurantDTO list.
     * @return employee restaurantDTO List.
     */
    public List<RestaurantDTO> getRestaurantList() {
        return restaurantList;
    }

    /**
     * Set employee restaurantDTO list.
     * @param restaurantList employee restaurantDTO list.
     */
    public void setRestaurantList(final List<RestaurantDTO> restaurantList) {
        this.restaurantList = restaurantList;
    }


    /**
     * Get employeeDTO schedule.
     * @return employeeDTO schedule.
     */
    public ScheduleDTO getSchedule() {
        return schedule;
    }

    /**
     * Set employeeDTO schedule.
     * @param schedule employeeDTO schedule.
     */
    public void setSchedule(final ScheduleDTO schedule) {
        this.schedule = schedule;
    }

    /**
     * Builder EmployeeDTO for schedule.
     * @param schedule schedule to build.
     * @return employeeDTO with schedule.
     */
    public EmployeeDTO schedule(final ScheduleDTO schedule){
        this.schedule = schedule;
        return this;
    }
}

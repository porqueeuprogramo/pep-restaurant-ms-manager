package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Employee Dto class
 */
public class EmployeeDTO {
    @JsonProperty("uid")
    private String uid;

    @JsonProperty("role")
    private String role;

    @JsonProperty("restaurantList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<RestaurantDTO> restaurantList = new HashSet<>();

    @JsonProperty("schedule")
    private ScheduleRoutineDTO schedule;

    /**
     * Get uid employeeDTO.
     * @return employeeDTO uid.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Set id employeeDTO.
     * @param uid employeeDTO uid.
     */
    public void setUid(final String uid) {
        this.uid = uid;
    }

    /**
     * Builder EmployeeDTO for uid.
     * @param uid uid to build.
     * @return employeeDTO with uid.
     */
    public EmployeeDTO uid(final String uid){
        this.uid = uid;
        return this;
    }

    /**
     * Get employeeDTO role.
     * @return employeeDTO role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Set employeeDTO role.
     * @param role employeeDTO role.
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * Builder EmployeeDTO for role.
     * @param role role to build.
     * @return employeeDTO with role.
     */
    public EmployeeDTO role(final String role){
        this.role = role;
        return this;
    }

    /**
     * Get employeeDTO restaurantDTO list.
     * @return employeeDTO restaurantDTO List.
     */
    public Set<RestaurantDTO> getRestaurantList() {
        return restaurantList;
    }

    /**
     * Set employeeDTO restaurantDTO list.
     * @param restaurantList employee restaurantDTO list.
     */
    public void setRestaurantList(final Set<RestaurantDTO> restaurantList) {
        this.restaurantList = restaurantList;
    }

    /**
     * Builder EmployeeDTO for restaurantList.
     * @param restaurantList restaurantList to build.
     * @return employeeDTO with restaurantList.
     */
    public EmployeeDTO restaurantList(final Set<RestaurantDTO> restaurantList){
        this.restaurantList = restaurantList;
        return this;
    }

    /**
     * Get employeeDTO schedule.
     * @return employeeDTO schedule.
     */
    public ScheduleRoutineDTO getSchedule() {
        return schedule;
    }

    /**
     * Set employeeDTO schedule.
     * @param schedule employeeDTO schedule.
     */
    public void setSchedule(final ScheduleRoutineDTO schedule) {
        this.schedule = schedule;
    }

    /**
     * Builder EmployeeDTO for schedule.
     * @param schedule schedule to build.
     * @return employeeDTO with schedule.
     */
    public EmployeeDTO schedule(final ScheduleRoutineDTO schedule){
        this.schedule = schedule;
        return this;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "uid=" + uid +
                ", role='" + role + '\'' +
                ", restaurantList=" + restaurantList +
                ", schedule=" + schedule +
                '}';
    }
}

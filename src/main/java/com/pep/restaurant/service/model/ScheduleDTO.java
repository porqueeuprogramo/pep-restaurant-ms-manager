package com.pep.restaurant.service.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.domain.enumeration.ScheduleType;

import java.util.ArrayList;
import java.util.List;

/**
 *  Schedule Dto.
 */
public class ScheduleDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("type")
    private ScheduleType type;

    @JsonProperty("employee")
    private List<Employee> employeeList = new ArrayList<>();

    /**
     * Get Schedule id.
     * @return schedule id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set schedule id.
     * @param id schedule id.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Get schedule type.
     * @return schedule typy enum.
     */
    public ScheduleType getType() {
        return type;
    }

    /**
     * Set schedule type
     * @param type schedule type enum.
     */
    public void setType(final ScheduleType type) {
        this.type = type;
    }

    /**
     * Builder Schedule for type.
     * @param type type to build.
     * @return schedule with type.
     */
    public ScheduleDTO type(final ScheduleType type){
        this.type = type;
        return this;
    }

    /**
     * Get schedule employee list.
     * @return employee list.
     */
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * Set schedule employee list.
     * @param employeeList employee list.
     */
    public void setEmployeeList(final List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}



package com.pep.restaurant.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pep.restaurant.domain.enumeration.ScheduleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Schedule Dto.
 */
public class ScheduleDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("type")
    private ScheduleType type;

    @JsonProperty("employeeList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EmployeeDTO> employeeList = new ArrayList<>();

    /**
     * Get Schedule id.
     *
     * @return schedule id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set schedule id.
     *
     * @param id schedule id.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Get schedule type.
     *
     * @return schedule typy enum.
     */
    public ScheduleType getType() {
        return type;
    }

    /**
     * Set schedule type
     *
     * @param type schedule type enum.
     */
    public void setType(final ScheduleType type) {
        this.type = type;
    }

    /**
     * Builder Schedule for type.
     *
     * @param type type to build.
     * @return schedule with type.
     */
    public ScheduleDTO type(final ScheduleType type) {
        this.type = type;
        return this;
    }

    /**
     * Get List of employees DTO.
     *
     * @return employee DTO list.
     */
    public List<EmployeeDTO> getEmployeeList() {
        return employeeList;
    }

    /**
     * Set employee DTO list.
     *
     * @param employeeList employee DTO list.
     */
    public void setEmployeeList(final List<EmployeeDTO> employeeList) {
        this.employeeList = employeeList;
    }
}



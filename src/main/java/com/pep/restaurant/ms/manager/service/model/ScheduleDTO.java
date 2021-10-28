package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pep.restaurant.ms.manager.domain.enumeration.ScheduleType;

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
     * Get ScheduleDTO id.
     *
     * @return scheduleDTO id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set scheduleDTO id.
     *
     * @param id scheduleDTO id.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Builder ScheduleDTO for id.
     * @param id id to build.
     * @return scheduleDTO with id.
     */
    public ScheduleDTO id(final long id){
        this.id = id;
        return this;
    }

    /**
     * Get scheduleDTO type.
     *
     * @return scheduleDTO type enum.
     */
    public ScheduleType getType() {
        return type;
    }

    /**
     * Set scheduleDTO type
     *
     * @param type scheduleDTO type enum.
     */
    public void setType(final ScheduleType type) {
        this.type = type;
    }

    /**
     * Builder ScheduleDTO for type.
     *
     * @param type type to build.
     * @return scheduleDTO with type.
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

    /**
     * Builder ScheduleDTO for employeeList.
     * @param employeeList employeeList to build.
     * @return scheduleDTO with employeeList.
     */
    public ScheduleDTO employeeList(final List<EmployeeDTO> employeeList){
        this.employeeList = employeeList;
        return this;
    }

}



package com.pep.restaurant.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "scheduleList")
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
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get schedule type.
     * @return schedule type.
     */
    public String getType() {
        return type;
    }

    /**
     * Set schedule type.
     * @param type schedule type.
     */
    public void setType(String type) {
        this.type = type;
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
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}



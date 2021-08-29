package com.pep.restaurant.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "role")
    private String role;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(
            name = "employee_schedule",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
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
    public Employee role(final String role){
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
    public Employee restaurant(final Restaurant restaurant){
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

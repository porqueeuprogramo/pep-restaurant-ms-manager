package com.pep.restaurant.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
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
    public void setId(long id) {
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
    public void setRole(String role) {
        this.role = role;
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
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}

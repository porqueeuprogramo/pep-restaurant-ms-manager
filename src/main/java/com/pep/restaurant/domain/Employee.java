package com.pep.restaurant.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

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
}

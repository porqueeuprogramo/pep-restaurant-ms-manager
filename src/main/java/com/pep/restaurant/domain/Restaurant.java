package com.pep.restaurant.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private int capacity;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(unique = true)
    private Menu menu;

    @OneToMany(
            mappedBy = "restaurant",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE}
    )
    private final List<Employee> employeeList = new ArrayList<>();

    /**
     * Method to add an employee on restaurant.
     * @param employee employee to add.
     */
    public void addEmployee(final Employee employee){
        employeeList.add(employee);
        employee.setRestaurant(this);
    }

    /**
     * Method to remove employee from restaurant.
     * @param employee employee to remove.
     */
    public void removeEmployee(final Employee employee){
        employeeList.remove(employee);
        employee.setRestaurant(this);
    }

    /**
     * Get Restaurant id.
     * @return restaurant id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set Restaurant Id.
     * @param id restaurant id.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Get Restaurant name.
     * @return restaurant name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Restaurant name.
     * @param name restaurant name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Builder Restaurant for name.
     * @param name name to build.
     * @return restaurant with name.
     */
    public Restaurant name(final String name){
        this.name = name;
        return this;
    }

    /**
     * Get Restaurant Location.
     * @return restaurant location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set Restaurant Location.
     * @param location restaurant location.
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * Builder Restaurant for location
     * @param location location to build.
     * @return restaurant with location.
     */
    public Restaurant location(final String location){
        this.location = location;
        return this;
    }

    /**
     * Get restaurant capacity.
     * @return restaurant capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set restaurant capacity.
     * @param capacity restaurant capacity.
     */
    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    /**
     * Builder for restaurant capacity.
     * @param capacity capacity to build.
     * @return restaurant with capacity.
     */
    public Restaurant capacity(final int capacity){
        this.capacity = capacity;
        return this;
    }

    /**
     * Get restaurant menu.
     * @return menu.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Set restaurant menu
     * @param menu restaurant menu.
     */
    public void setMenu(final Menu menu) {
        this.menu = menu;
    }

    /**
     * Builder Restaurant for menu.
     * @param menu menu to build.
     * @return restaurant with menu.
     */
    public Restaurant menu(final Menu menu){
        this.menu = menu;
        return this;
    }

}

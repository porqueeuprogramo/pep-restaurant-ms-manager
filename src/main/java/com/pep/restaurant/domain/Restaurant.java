package com.pep.restaurant.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;


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

}

package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Restaurant Dto class
 */
public class LocationDTO implements Serializable {

    @JsonProperty("id")
    private long id;

    @JsonProperty("address")
    private AddressDTO address;

    @JsonProperty("coordinate")
    private CoordinateDTO locationCoordinate;

    /**
     * Method to get a LocationDTO id.
     * @return id.
     */
    public long getId() {
        return id;
    }

    /**
     * Method to set a LocationDTO id.
     * @param id to be set.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Builder LocationDTO for id.
     * @param id id to build.
     * @return locationDTO with id.
     */
    public LocationDTO id(final long id){
        this.id = id;
        return this;
    }

    /**
     * Method to get a LocationDTO address.
     * @return address
     */
    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Method to set a LocationDTO address.
     * @param address address to be set.
     */
    public void setAddress(final AddressDTO address) {
        this.address = address;
    }

    /**
     * Builder LocationDTO for address.
     * @param address address to build.
     * @return locationDTO with address.
     */
    public LocationDTO address(final AddressDTO address){
        this.address = address;
        return this;
    }

    /**
     * Get LocationDTO Coordinate.
     * @return LocationDTO Coordinate.
     */
    public CoordinateDTO getLocationCoordinate() {
        return locationCoordinate;
    }

    /**
     * Set LocationDTO Coordinate.
     * @param locationCoordinate LocationDTO Coordinate.
     */
    public void setLocationCoordinate(final CoordinateDTO locationCoordinate) {
        this.locationCoordinate = locationCoordinate;
    }

    /**
     * Builder LocationDTO for Coordinate.
     * @param locationCoordinate CoordinateDTO to build.
     * @return locationDTO with Coordinate.
     */
    public LocationDTO locationCoordinate(final CoordinateDTO locationCoordinate){
        this.locationCoordinate = locationCoordinate;
        return this;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "id=" + id +
                ", address=" + address +
                ", locationCoordinate=" + locationCoordinate +
                '}';
    }
}

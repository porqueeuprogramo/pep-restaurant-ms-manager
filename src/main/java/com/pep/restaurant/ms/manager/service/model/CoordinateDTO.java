package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CoordinateDTO implements Serializable {
    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    /**
     * Method to get a CoordinateDTO latitude.
     * @return latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Method to set a RestaurantDTO latitude.
     * @param latitude to be set.
     */
    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    /**
     * Builder CoordinateDTO for latitude.
     * @param  latitude to build.
     * @return CoordinateDTO with latitude.
     */
    public CoordinateDTO latitude(final double latitude){
        this.latitude = latitude;
        return this;
    }

    /**
     * Method to get a CoordinateDTO longitude.
     * @return longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Method to set a CoordinateDTO longitude.
     * @param longitude to be set.
     */
    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    /**
     * Builder CoordinateDTO for longitude.
     * @param  longitude to build.
     * @return CoordinateDTO with longitude.
     */
    public CoordinateDTO longitude(final double longitude){
        this.longitude = longitude;
        return this;
    }

    @Override
    public String toString() {
        return "CoordinateDTO{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

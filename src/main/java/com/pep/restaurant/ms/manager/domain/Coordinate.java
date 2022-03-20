package com.pep.restaurant.ms.manager.domain;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private double latitude;
    private double longitude;

    /**
     * Get latitude.
     * @return latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set latitude.
     * @param latitude latitude.
     */
    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    /**
     * Builder Coordinate for latitude.
     * @param  latitude to build.
     * @return Coordinate with latitude.
     */
    public Coordinate latitude(final double latitude){
        this.latitude = latitude;
        return this;
    }

    /**
     * Get Longitude.
     * @return longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set longitude.
     * @param longitude longitude.
     */
    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    /**
     * Builder Coordinate for longitude.
     * @param  longitude to build.
     * @return Coordinate with longitude.
     */
    public Coordinate longitude(final double longitude){
        this.longitude = longitude;
        return this;
    }
}

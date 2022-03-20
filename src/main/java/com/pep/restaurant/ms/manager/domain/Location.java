package com.pep.restaurant.ms.manager.domain;

import com.pep.restaurant.ms.manager.domain.util.CoordinateSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.io.IOException;
import java.util.Arrays;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(unique = true)
    private Address address;

    @Column(name = "coordinate")
    private byte[] locationCoordinate;

    /**
     * Get Location id.
     * @return location id.
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
     * Builder Location for id.
     * @param id id to build.
     * @return location with id.
     */
    public Location id(final long id){
        this.id = id;
        return this;
    }

    /**
     * Get Location Address.
     * @return Location Address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set Location Address.
     * @param address location address.
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Builder Location for address.
     * @param address address to build.
     * @return location with address.
     */
    public Location address(final Address address){
        this.address = address;
        return this;
    }

    /**
     * Get Location Coordinate.
     * @return Location Coordinate.
     */
    public byte[] getLocationCoordinate() {
        return locationCoordinate;
    }

    /**
     * Set Location Coordinate.
     * @param locationCoordinate Location Coordinate.
     */
    public void setLocationCoordinate(final byte[] locationCoordinate) {
        this.locationCoordinate = locationCoordinate;
    }

    /**
     * Builder Location for Coordinate.
     * @param coordinate Coordinate to build.
     * @return location with Coordinate.
     */
    public Location locationCoordinate(final Coordinate coordinate){
        if(coordinate != null){{
            try{
                this.locationCoordinate = CoordinateSerializer.toByteArray(coordinate);
            }catch(IOException ioException){
                this.locationCoordinate = null;
            }
        }}
        return this;
    }

    /**
     * Get Coordinate for Location.
     * @return Location coordinate.
     */
    public Coordinate getCoordinate() {
        Coordinate coordinate = null;
        if(locationCoordinate != null) {
            try{
               coordinate = CoordinateSerializer.fromByteArray(locationCoordinate);
            }catch(IOException ioException){
               coordinate = null;
            }
        }
        return coordinate;
    }

    /**
     * Set Location Coordinate.
     * @param coordinate coordinate.
     * @throws IOException exception.
     */
    public void setCoordinate(final Coordinate coordinate) throws IOException {
        if(coordinate != null){{
            try{
                this.locationCoordinate = CoordinateSerializer.toByteArray(coordinate);
            }catch(IOException ioException){
                this.locationCoordinate = null;
            }
        }}
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", address=" + address +
                ", locationCoordinate=" + Arrays.toString(locationCoordinate) +
                '}';
    }
}

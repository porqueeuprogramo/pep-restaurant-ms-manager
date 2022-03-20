package com.pep.restaurant.ms.manager.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    /**
     * Get address id.
     * @return address id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set address Id.
     * @param id address id.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Builder Address for id.
     * @param id id to build.
     * @return address with id.
     */
    public Address id(final long id){
        this.id = id;
        return this;
    }

    /**
     * Get address name.
     * @return address name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set address name.
     * @param name address name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Builder Address for name.
     * @param name name to build.
     * @return address with name.
     */
    public Address name(final String name){
        this.name = name;
        return this;
    }

    /**
     * Get address postal code.
     * @return address postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Set address postal code.
     * @param postalCode address postal code.
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Builder Address for postal code.
     * @param postalCode postal code to build.
     * @return address with postal code.
     */
    public Address postalCode(final String postalCode){
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Get address city.
     * @return address city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set address city.
     * @param city address city.
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Builder Address for city.
     * @param city city to build.
     * @return address with city.
     */
    public Address city(final String city){
        this.city = city;
        return this;
    }

    /**
     * Get address country.
     * @return address country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set address country.
     * @param country address country.
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Builder Address for country.
     * @param country country to build.
     * @return address with country.
     */
    public Address country(final String country){
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

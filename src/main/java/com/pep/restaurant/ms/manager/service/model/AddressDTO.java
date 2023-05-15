package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    /**
     * Method to get addressDTO name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set addressDTO name.
     * @param name to be set.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Builder AddressDTO for name.
     * @param name name to build.
     * @return addressDTO with name.
     */
    public AddressDTO name(final String name){
        this.name = name;
        return this;
    }

    /**
     * Method to get addressDTO postal code.
     * @return postalCode.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Method to set addressDTO postal code.
     * @param postalCode to be set.
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Builder AddressDTO for postal code.
     * @param postalCode postal code to build.
     * @return addressDTO with postal code.
     */
    public AddressDTO postalCode(final String postalCode){
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Method to get addressDTO city.
     * @return city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Method to set addressDTO city.
     * @param city to be set.
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Builder AddressDTO for city.
     * @param city city to build.
     * @return addressDTO with city.
     */
    public AddressDTO city(final String city){
        this.city = city;
        return this;
    }

    /**
     * Method to get addressDTO country.
     * @return country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Method to set addressDTO country.
     * @param country to be set.
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Builder AddressDTO for country.
     * @param country country to build.
     * @return addressDTO with country.
     */
    public AddressDTO country(final String country){
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                ", name='" + name + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

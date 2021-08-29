package com.pep.restaurant.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Menu Dto class
 */
public class MenuDTO implements Serializable {

    @JsonProperty("id")
    private long id;

    @JsonProperty("language")
    private String language;

    /**
     * Get Menu id.
     * @return menu id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set menu id.
     * @param id id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get Menu language.
     * @return menu language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set menu language.
     * @param language language.
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

}
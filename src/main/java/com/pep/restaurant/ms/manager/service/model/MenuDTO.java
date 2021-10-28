package com.pep.restaurant.ms.manager.service.model;

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
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Builder MenuDTO for id.
     * @param id id to build.
     * @return menuDTO with id.
     */
    public MenuDTO id(final long id){
        this.id = id;
        return this;
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

    /**
     * Builder MenuDTO for language.
     * @param language language to build.
     * @return menuDTO with language.
     */
    public MenuDTO language(final String language){
        this.language = language;
        return this;
    }

}

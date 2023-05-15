package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Menu Dto class
 */
public class MenuDTO implements Serializable {

    @JsonProperty("language")
    private String language;

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

    @Override
    public String toString() {
        return "Menu{" +
                "language='" + language + '\'' +
                '}';
    }
}

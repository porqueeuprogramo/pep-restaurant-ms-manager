package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pep.restaurant.ms.manager.domain.Menu;

import java.io.Serializable;

/**
 * Menu Dto class
 */
public class MenuDTO implements Serializable {

    @JsonProperty("language")
    private String language;

    @JsonProperty("uid")
    private String uid;

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

    /**
     * Get MenuDTO uid.
     * @return menuDto uid.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Set menu uid.
     * @param uid uid.
     */
    public void setUid(final String uid) {
        this.uid = uid;
    }

    /**
     * Builder MenuDTO for uid.
     * @param uid uid to build.
     * @return menuDTO with uid.
     */
    public MenuDTO uid(final String uid){
        this.uid = uid;
        return this;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "language='" + language + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}

package com.pep.restaurant.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "language")
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
    public void setLanguage(String language) {
        this.language = language;
    }
}

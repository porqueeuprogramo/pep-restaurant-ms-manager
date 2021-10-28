package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * User class.
 */

public class UserDTO implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    /**
     * User Get id.
     * @return user id.
     */
    public String getId() {
        return id;
    }

    /**
     * User Set id.
     * @param id user id;
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * User get Username.
     * @return user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * User set username.
     * @param userName username.
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Builder for username.
     * @param username username.
     * @return user username.
     */
    public UserDTO username(final String username){
        this.userName = username;
        return this;
    }

    /**
     * User get First Name.
     * @return first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * User set First Name.
     * @param firstName first name.
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Builder for firstName.
     * @param firstName firstName.
     * @return user firstName.
     */
    public UserDTO firstName(final String firstName){
        this.firstName = firstName;
        return this;
    }

    /**
     * User get Last Name.
     * @return last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * User set last name.
     * @param lastName last name.
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Builder for lastName.
     * @param lastName lastName.
     * @return user lastName.
     */
    public UserDTO lastName(final String lastName){
        this.lastName = lastName;
        return this;
    }

    /**
     * User get email.
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * User set email.
     * @param email email.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Builder for email.
     * @param email email.
     * @return user email.
     */
    public UserDTO email(final String email){
        this.email = email;
        return this;
    }

    /**
     * User get Password.
     * @return user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * User set password.
     * @param password password.
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Builder for password.
     * @param password password.
     * @return user password.
     */
    public UserDTO password(final String password){
        this.password = password;
        return this;
    }
}

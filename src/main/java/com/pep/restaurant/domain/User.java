package com.pep.restaurant.domain;

/**
 * User class.
 */
public class User {

    private String id;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

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
     * Builder for id.
     * @param id id.
     * @return user id.
     */
    public User id(final String id){
        this.id = id;
        return this;
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
    public User username(final String username){
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
    public User firstName(final String firstName){
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
    public User lastName(final String lastName){
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
    public User email(final String email){
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
    public User password(final String password){
        this.password = password;
        return this;
    }
}

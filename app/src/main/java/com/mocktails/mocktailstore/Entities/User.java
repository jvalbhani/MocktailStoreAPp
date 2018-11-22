package com.mocktails.mocktailstore.Entities;

import java.io.Serializable;

/**
 *
 * @author jayesh
 */

public class User implements Serializable {
    private String contactNo;
    private String email;
    protected String password;
    protected String firstName;
    protected String lastName;

    public User() {

    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(", contactNo=").append(contactNo).append(", email=").append(email)
                .append(", password=").append(password).append(", firstName=").append(firstName).append(", lastName=")
                .append(lastName).append("]");
        return builder.toString();
    }

}

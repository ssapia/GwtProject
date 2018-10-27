package com.mycompany.mywebapp.server.user;

import com.mycompany.mywebapp.shared.user.User;

public class UserImpl implements User {

    private String firstName;
    private String lastName;

    public UserImpl(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

package com.example.foodapp.models;

public class ModelUser {

    // sets up variables to store each info about user, identified by an id
    int id;
    String firstName;
    String lastName;
    String email;
    String password;
    String userImage;

    // class initialization of creating and storing user information, with and without an id
    public ModelUser(String firstName, String lastName, String email, String password, String userImage){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userImage = userImage;
    }
    public ModelUser(int id, String firstName, String lastName, String email, String password, String userImage){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userImage = userImage;
    }


    // getters and setters for each part of the stored user's info
    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}

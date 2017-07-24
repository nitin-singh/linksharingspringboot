package com.ls.dto.user;

public class UserDTO {


    public String email;
    public String username;
    public String password;
    public String lastPassword;
    public String firstName;
    public String lastName;
    public Boolean isAdmin = false;
    public Boolean isActive = true;
    public byte[] profilePhoto;
    public String confirmPassword;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {

        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public Boolean getActive() {
        return isActive;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}

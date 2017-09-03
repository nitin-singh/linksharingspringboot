package com.ls.dto;

import java.io.Serializable;

public class SampleDTO implements Serializable {
    public String firstName;
    public String middleName;
    public String lastName;
    public String phoneNumber;
    public String address;

    public SampleDTO(String firstName, String middleName, String lastName, String phoneNumber, String address) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
